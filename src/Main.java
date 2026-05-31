import br.ufma.extensao.entidades.Administrador;
import br.ufma.extensao.enums.*;
import br.ufma.extensao.menus.*;
import br.ufma.extensao.servicos.*;

import java.util.Scanner;

public class Main {

    // Helpers de log
    static void titulo(String t) {
        System.out.println("\n╔══════════════════════════════════════════════════════════");
        System.out.println("║ " + t);
        System.out.println("╚══════════════════════════════════════════════════════════");
    }
    static void acao(String s)  { System.out.println("▶ AÇÃO    : " + s); }
    static void ok(String s)    { System.out.println("  ✓ OK    : " + s); }
    static void info(String s)  { System.out.println("  • INFO  : " + s); }
    static void fluxo(String s) { System.out.println("  → FLUXO : " + s); }
    static void anomalia(String s) { System.out.println("⚠ ANOMALIA: " + s); }
    static void bloqueado(String s){ System.out.println("  ✗ BLOQUEADO: " + s); }

    public static void main(String[] args) {

        // ═══════════════════════════════════════════════════════
        titulo("PASSO 1 — INSTANCIAÇÃO DOS SERVIÇOS");
        // ═══════════════════════════════════════════════════════
        acao("Instanciando todos os serviços com injeção de dependências");
        PPCService ppcService                   = new PPCService();
        UsuarioService usuarioService           = new UsuarioService(ppcService);
        InscricaoService inscricaoService       = new InscricaoService();
        CertificadoService certificadoService   = new CertificadoService();
        OportunidadeService oportunidadeService = new OportunidadeService(inscricaoService, certificadoService);
        AproveitamentoService aprovService      = new AproveitamentoService();
        GrupoService grupoService               = new GrupoService();
        ok("PPCService, UsuarioService(ppcService), InscricaoService, CertificadoService");
        ok("OportunidadeService(inscricaoService, certificadoService), AproveitamentoService, GrupoService");

        // ═══════════════════════════════════════════════════════
        titulo("PASSO 2 — CADASTRO DE GESTORES (RF003)");
        // ═══════════════════════════════════════════════════════
        info("Usuario é abstract — Admin representado por Coordenador com papel ADMIN (TODO: classe Admin concreta)");
        Coordenador admin = new Coordenador("ADM-001", "Admin Sistema", "admin@ufma.br", "admin123", "0000000", CargoCoordenador.COORDENADOR);
        admin.setPapel(Papel.ADMIN);
        ok("Admin criado: " + admin.getNome() + " | papel=" + admin.getPapel().getDescricao());

        acao("Admin cadastrando Docente");
        Docente docente = usuarioService.cadastrarDocente(
                admin, "Prof. Carlos Silva", "carlos.silva@docente.ufma.br",
                "senha123", "1234567", "Departamento de Informática"
        );
        ok("Docente cadastrado: " + docente.getNome() + " | SIAPE=" + docente.getSiape());

        acao("Admin cadastrando Coordenador");
        Coordenador coordenador = (Coordenador) usuarioService.cadastrarCoordenador(
                admin, "Coord. Mariana Lima", "mariana.lima@ufma.br",
                "senha789", "7654321", CargoCoordenador.COORDENADOR
        );
        ok("Coordenador cadastrado: " + coordenador.getNome());

        anomalia("Coordenador (não-admin) tentando cadastrar docente");
        try {
            usuarioService.cadastrarDocente(coordenador, "Fake", "fake@ufma.br", "123", "999", "Depto");
            System.out.println("  ✗ ERRO: deveria ter bloqueado!");
        } catch (IllegalStateException e) {
            bloqueado(e.getMessage());
        }

        // ═══════════════════════════════════════════════════════
        titulo("PASSO 3 — PPC E MÚLTIPLOS CURSOS (RF007/008)");
        // ═══════════════════════════════════════════════════════
        Curso cursoCC = new Curso("1L", "Ciência da Computação", "CC");
        Curso cursoEng = new Curso("2M", "Engenharia da Computação", "EC");
        info("Dois cursos criados: " + cursoCC.getNome() + " e " + cursoEng.getNome());

        anomalia("Buscar PPC vigente em curso ainda sem PPC");
        try {
            ppcService.buscarVigente(cursoCC);
        } catch (Exception e) {
            bloqueado(e.getMessage());
        }

        acao("Coordenador criando PPC 2025.1 do curso CC");
        PPC ppcCC_2025 = ppcService.criarPPC(coordenador, cursoCC, "2025.1", 345.0);
        ok("PPC criado: v" + ppcCC_2025.getVersao() + " | carga=" + ppcCC_2025.getCargaHoraria() + "h | início=" + ppcCC_2025.getDataInicio());
        fluxo("Empilhado como vigente no Deque do curso CC");

        acao("Coordenador criando PPC 2025.1 do curso Eng (independente)");
        PPC ppcEng = ppcService.criarPPC(coordenador, cursoEng, "2025.1", 300.0);
        ok("PPC Eng criado: v" + ppcEng.getVersao() + " | carga=" + ppcEng.getCargaHoraria() + "h");
        info("Dois PPCs vigentes ao mesmo tempo em cursos diferentes — pilhas separadas no Map");

        // ═══════════════════════════════════════════════════════
        titulo("PASSO 4 — DISCENTES INGRESSANTES NO PPC 2025.1");
        // ═══════════════════════════════════════════════════════
        info("Cada discente fixa o PPC vigente do curso no momento do cadastro");
        acao("Cadastrando 3 discentes do curso CC");
        Discente ana = usuarioService.cadastrarDiscente("Ana Souza", "ana@discente.ufma.br", "s1", "20250001", 3, cursoCC);
        Discente bruno = usuarioService.cadastrarDiscente("Bruno Costa", "bruno@discente.ufma.br", "s2", "20250002", 2, cursoCC);
        Discente carla = usuarioService.cadastrarDiscente("Carla Mendes", "carla@discente.ufma.br", "s3", "20250003", 1, cursoCC);
        ok(ana.getNome() + " | PPC fixado: v" + ana.getPpc().getVersao());
        ok(bruno.getNome() + " | PPC fixado: v" + bruno.getPpc().getVersao());
        ok(carla.getNome() + " | PPC fixado: v" + carla.getPpc().getVersao());

        // ═══════════════════════════════════════════════════════
        titulo("PASSO 5 — REFORMA CURRICULAR: NOVO PPC 2026.1");
        // ═══════════════════════════════════════════════════════
        acao("Coordenador criando PPC 2026.1 do curso CC");
        PPC ppcCC_2026 = ppcService.criarPPC(coordenador, cursoCC, "2026.1", 360.0);
        ok("Novo PPC vigente: v" + ppcCC_2026.getVersao() + " | carga=" + ppcCC_2026.getCargaHoraria() + "h");
        fluxo("PPC anterior (v2025.1) recebeu dataFim automaticamente: " + ppcCC_2025.getDataFim());
        info("Discentes já cadastrados MANTÊM o PPC antigo (regra de integralização)");

        acao("Cadastrando Diego — ingressante APÓS a reforma");
        Discente diego = usuarioService.cadastrarDiscente("Diego Alves", "diego@discente.ufma.br", "s4", "20260001", 1, cursoCC);
        ok(diego.getNome() + " | PPC fixado: v" + diego.getPpc().getVersao());

        info("COMPARAÇÃO — mesmo curso, PPCs diferentes:");
        fluxo(ana.getNome() + " (ingresso 2025) → v" + ana.getPpc().getVersao() + " exige " + ana.getPpc().getCargaHoraria() + "h");
        fluxo(diego.getNome() + " (ingresso 2026) → v" + diego.getPpc().getVersao() + " exige " + diego.getPpc().getCargaHoraria() + "h");

        acao("Listando histórico de PPCs do curso CC");
        List<PPC> historico = ppcService.listarHistorico(cursoCC);
        info("Total de versões: " + historico.size());
        for (PPC p : historico) {
            fluxo("v" + p.getVersao() + " | início=" + p.getDataInicio() + " | fim=" + (p.getDataFim() == null ? "VIGENTE" : p.getDataFim()));
        }

        acao("Buscando PPC por versão '2025.1'");
        PPC encontrado = ppcService.buscarPPCPorVersao(cursoCC, "2025.1");
        ok("Encontrado: v" + encontrado.getVersao() + " | encerrado em " + encontrado.getDataFim());

        anomalia("Buscar versão inexistente '1999.1'");
        try {
            ppcService.buscarPPCPorVersao(cursoCC, "1999.1");
        } catch (Exception e) {
            bloqueado(e.getMessage());
        }

        // ═══════════════════════════════════════════════════════
        titulo("PASSO 6 — DESATIVAR E ANONIMIZAR (RF0001)");
        // ═══════════════════════════════════════════════════════
        acao("Cadastrando discente que será removido");
        Discente removivel = usuarioService.cadastrarDiscente("Para Remover", "remover@discente.ufma.br", "r1", "20260099", 1, cursoCC);
        ok("Cadastrado: " + removivel.getNome());

        acao("Admin desativando o discente");
        usuarioService.desativar(admin, removivel);
        fluxo("ativo agora = " + removivel.isAtivo());

        acao("Admin anonimizando o discente");
        usuarioService.anonimizar(admin, removivel);
        fluxo("nome → '" + removivel.getNome() + "'");
        fluxo("email → '" + removivel.getEmail() + "'");

        acao("Coordenador desativando um discente (permitido pela hierarquia)");
        Discente removivel2 = usuarioService.cadastrarDiscente("Outro Removivel", "outro@discente.ufma.br", "r2", "20260098", 1, cursoCC);
        usuarioService.desativar(coordenador, removivel2);
        ok("Coordenador desativou discente: ativo=" + removivel2.isAtivo());

        anomalia("Discente tentando desativar coordenador");
        try { usuarioService.desativar(ana, coordenador); }
        catch (IllegalStateException e) { bloqueado(e.getMessage()); }

        anomalia("Coordenador tentando desativar docente (não é discente)");
        try { usuarioService.desativar(coordenador, docente); }
        catch (IllegalStateException e) { bloqueado(e.getMessage()); }

        // ═══════════════════════════════════════════════════════
        titulo("PASSO 7 — OPORTUNIDADES (CRIAR/BUSCAR/LISTAR/CANCELAR)");
        // ═══════════════════════════════════════════════════════
        info("Datas: início no passado, fim HOJE — necessário para passar em criar/iniciar/encerrar");
        acao("Docente criando oportunidade 'Monitoria de POO' (2 vagas)");
        Oportunidade oportunidade = oportunidadeService.criarOportunidade(
                "Monitoria de POO", "Auxílio em LP2",
                TipoOportunidade.PROJETO, Modalidade.PRESENCIAL,
                60, 2, null, docente,
                LocalDate.now().minusMonths(1), LocalDate.now()
        );
        ok("Criada: " + oportunidade.getTitulo() + " | status=" + oportunidade.getStatus());

        acao("Buscando oportunidade por ID");
        Oportunidade buscada = oportunidadeService.buscarOportunidadePorId(oportunidade.getId());
        ok("Encontrada: " + buscada.getTitulo());

        acao("Docente publicando a oportunidade");
        oportunidadeService.publicarOportunidade(oportunidade.getId(), docente);
        fluxo("Docente publica → status vai direto para ABERTA: " + oportunidade.getStatus());

        acao("Criando e cancelando uma segunda oportunidade");
        Oportunidade opCancelar = oportunidadeService.criarOportunidade(
                "Evento Teste", "Para cancelar", TipoOportunidade.EVENTO, Modalidade.REMOTO,
                10, 5, null, docente, LocalDate.now(), LocalDate.now().plusMonths(2)
        );
        oportunidadeService.cancelarOportunidade(opCancelar.getId(), docente);
        ok("Cancelada: " + opCancelar.getTitulo() + " | status=" + opCancelar.getStatus());

        acao("Listando todas as oportunidades");
        Set<Oportunidade> todas = oportunidadeService.listarOportunidades();
        info("Total no sistema: " + todas.size());

        // ═══════════════════════════════════════════════════════
        titulo("PASSO 8 — INSCRIÇÕES E FILA DE ESPERA (RF017)");
        // ═══════════════════════════════════════════════════════
        info("4 discentes para 2 vagas → 2 entram, 2 vão para LISTA_DE_ESPERA");
        acao("Inscrevendo Ana, Bruno, Carla e Diego");
        Inscricao insAna   = inscricaoService.inscrever(ana, oportunidade, "Quero aprender");
        Inscricao insBruno = inscricaoService.inscrever(bruno, oportunidade, "Experiência em Java");
        Inscricao insCarla = inscricaoService.inscrever(carla, oportunidade, "Quero contribuir");
        Inscricao insDiego = inscricaoService.inscrever(diego, oportunidade, "Tenho interesse");
        fluxo(ana.getNome() + " → " + insAna.getStatus() + " (dentro das vagas)");
        fluxo(bruno.getNome() + " → " + insBruno.getStatus() + " (dentro das vagas)");
        fluxo(carla.getNome() + " → " + insCarla.getStatus() + " (fila de espera)");
        fluxo(diego.getNome() + " → " + insDiego.getStatus() + " (fila de espera)");

        info("Total inscritos: " + inscricaoService.listarPorOportunidade(oportunidade).size());
        info("Inscrições de " + ana.getNome() + ": " + inscricaoService.listarPorDiscente(ana).size());

        anomalia("Inscrever discente já inscrito");
        try { inscricaoService.inscrever(ana, oportunidade, "De novo"); }
        catch (IllegalStateException e) { bloqueado(e.getMessage()); }

        acao("Docente aprovando os 2 PENDENTE (Ana e Bruno)");
        inscricaoService.aprovar(insAna.getId(), oportunidade, docente);
        inscricaoService.aprovar(insBruno.getId(), oportunidade, docente);
        fluxo(ana.getNome() + " → " + insAna.getStatus());
        fluxo(bruno.getNome() + " → " + insBruno.getStatus());

        anomalia("Aprovar com vagas cheias (Carla está na fila)");
        try { inscricaoService.aprovar(insCarla.getId(), oportunidade, docente); }
        catch (IllegalStateException e) { bloqueado(e.getMessage()); }

        anomalia("Bruno tentando desistir da inscrição de Ana");
        try { inscricaoService.desistir(insAna.getId(), oportunidade, bruno); }
        catch (IllegalStateException e) { bloqueado(e.getMessage()); }

        acao("Ana desiste → promoverFilaEspera deve subir o próximo");
        inscricaoService.desistir(insAna.getId(), oportunidade, ana);
        fluxo(ana.getNome() + " → " + insAna.getStatus());
        fluxo(carla.getNome() + " promovida da fila → " + insCarla.getStatus());

        acao("Bruno desiste → promove o próximo da fila");
        inscricaoService.desistir(insBruno.getId(), oportunidade, bruno);
        fluxo(bruno.getNome() + " → " + insBruno.getStatus());
        fluxo(diego.getNome() + " promovido da fila → " + insDiego.getStatus());
        info("Fila de espera restante: " + inscricaoService.listarFilaEspera(oportunidade).size());

        acao("Docente aprovando Carla e Diego (agora PENDENTE)");
        inscricaoService.aprovar(insCarla.getId(), oportunidade, docente);
        inscricaoService.aprovar(insDiego.getId(), oportunidade, docente);
        fluxo(carla.getNome() + " → " + insCarla.getStatus());
        fluxo(diego.getNome() + " → " + insDiego.getStatus());

        acao("Docente removendo Carla (APROVADA) com justificativa");
        inscricaoService.rejeitarRemoverDiscente(insCarla.getId(), "Conflito de horário", oportunidade, docente);
        fluxo(carla.getNome() + " → " + insCarla.getStatus() + " | justificativa registrada");
        info("Resta apenas Diego APROVADO — só ele receberá certificado");

        anomalia("Remover sem justificativa");
        try { inscricaoService.rejeitarRemoverDiscente(insDiego.getId(), "", oportunidade, docente); }
        catch (IllegalArgumentException e) { bloqueado(e.getMessage()); }

        // ═══════════════════════════════════════════════════════
        titulo("PASSO 9 — ENCERRAMENTO E CERTIFICADOS (RF019)");
        // ═══════════════════════════════════════════════════════
        acao("Iniciando execução da oportunidade");
        oportunidadeService.iniciarExecucao(oportunidade.getId());
        fluxo("status → " + oportunidade.getStatus());

        acao("Encerrando oportunidade (fim=hoje permite encerrar)");
        oportunidadeService.encerrarOportunidade(oportunidade.getId());
        fluxo("status → " + oportunidade.getStatus());
        fluxo("Certificados gerados automaticamente para inscrições APROVADAS");

        List<Certificado> certificados = oportunidadeService.pegarCertificadosDeOportunidade(oportunidade);
        info("Certificados gerados: " + certificados.size());
        for (Certificado c : certificados) {
            fluxo(c.getDiscente().getNome() + " | " + c.getCargaHorariaCumprida() + "h | código=" + c.getCodigoAutenticidade());
        }

        if (!certificados.isEmpty()) {
            acao("Buscando certificado por código");
            String codigo = certificados.get(0).getCodigoAutenticidade();
            Certificado certBuscado = certificadoService.buscar(codigo);
            ok("Encontrado: " + certBuscado.getCodigoAutenticidade() + " → " + certBuscado.getDiscente().getNome());
            info("Certificados de " + diego.getNome() + ": " + certificadoService.listarPorDiscente(diego).size());
        }

        // ═══════════════════════════════════════════════════════
        titulo("PASSO 10 — APROVEITAMENTO (RF021)");
        // ═══════════════════════════════════════════════════════
        acao(ana.getNome() + " submetendo solicitação de aproveitamento");
        Aproveitamento aprov = aprovService.submeter(ana.getId(), "Workshop externo de Java", 20.0);
        fluxo("status → " + aprov.getStatus());
        info("Pendentes no sistema: " + aprovService.listarPendentes().size());
        info("Aproveitamentos de " + ana.getNome() + ": " + aprovService.listarPorDiscente(ana.getId()).size());

        anomalia("Indeferir sem parecer");
        try { aprovService.indeferir(aprov.getId(), coordenador, ""); }
        catch (IllegalArgumentException e) { bloqueado(e.getMessage()); }

        acao("Coordenador indeferindo com parecer");
        aprovService.indeferir(aprov.getId(), coordenador, "Documentação insuficiente");
        fluxo("status → " + aprov.getStatus());

        acao(ana.getNome() + " reenviando após correção");
        aprovService.reenviar(aprov.getId());
        fluxo("status → " + aprov.getStatus());

        acao("Coordenador aprovando");
        aprovService.aprovar(aprov.getId(), coordenador, aprov.getCargaHorariaPleiteada());
        fluxo("status → " + aprov.getStatus());

        // ═══════════════════════════════════════════════════════
        titulo("PASSO 11 — GRUPOS E CARGOS (RF009/010)");
        // ═══════════════════════════════════════════════════════
        acao("Criando grupo estudantil");
        Grupo grupo = grupoService.criar("Diretório Acadêmico de Computação", "Representação estudantil", "dac@discente.ufma.br", docente.getId());
        ok("Grupo criado: " + grupo.getNome() + " | status=" + grupo.getStatus());

        acao("Adicionando membros");
        GrupoMembros mAna   = grupoService.adicionarMembro(grupo.getId(), ana.getId(), Cargo.MEMBRO);
        GrupoMembros mBruno = grupoService.adicionarMembro(grupo.getId(), bruno.getId(), Cargo.VICE);
        fluxo(ana.getNome() + " → " + mAna.getCargo());
        fluxo(bruno.getNome() + " → " + mBruno.getCargo());

        acao("Reatribuindo cargo de Ana: MEMBRO → TESOUREIRO");
        grupoService.reatribuirCargo(mAna.getId(), Cargo.TESOUREIRO);
        fluxo(ana.getNome() + " → " + mAna.getCargo());

        anomalia("Reatribuir para o mesmo cargo");
        try { grupoService.reatribuirCargo(mAna.getId(), Cargo.TESOUREIRO); }
        catch (IllegalStateException e) { bloqueado(e.getMessage()); }

        anomalia("Reatribuir membro inexistente");
        try { grupoService.reatribuirCargo("MBR-INEXISTENTE", Cargo.DIRETOR); }
        catch (Exception e) { bloqueado(e.getMessage()); }

        acao("Listando membros do grupo");
        for (GrupoMembros m : grupoService.listarMembrosPorGrupo(grupo.getId())) {
            fluxo(m.getUsuarioId() + " | " + m.getCargo());
        }

        acao("Removendo Bruno do grupo");
        grupoService.removerMembro(mBruno.getId());
        fluxo(bruno.getNome() + " removido | dataSaida=" + mBruno.getDataSaida());

        anomalia("Reatribuir cargo de membro removido (inativo)");
        try { grupoService.reatribuirCargo(mBruno.getId(), Cargo.DIRETOR); }
        catch (IllegalStateException e) { bloqueado(e.getMessage()); }

        acao("Promovendo Diego a DiscenteDiretor");
        DiscenteDiretor diretor = usuarioService.promover(diego, grupo, "Diretor");
        fluxo(diretor.getNome() + " → papel=" + diretor.getPapel().getDescricao());
        fluxo("PPC mantido após promoção: v" + diretor.getPpc().getVersao());

        // ═══════════════════════════════════════════════════════
        titulo("RESULTADO FINAL — RESUMO DO SISTEMA");
        // ═══════════════════════════════════════════════════════
        info("Usuários cadastrados : " + usuarioService.listarUsuarios().size());
        info("Docente              : " + docente.getNome());
        info("Coordenador          : " + coordenador.getNome());
        info("Discente PPC antigo  : " + ana.getNome() + " (v" + ana.getPpc().getVersao() + ")");
        info("Discente PPC novo    : " + diego.getNome() + " (v" + diego.getPpc().getVersao() + ")");
        info("PPC vigente CC       : v" + ppcService.buscarVigente(cursoCC).getVersao());
        info("PPC vigente Eng      : v" + ppcService.buscarVigente(cursoEng).getVersao());
        info("Oportunidade         : " + oportunidade.getTitulo() + " | " + oportunidade.getStatus());
        info("Certificados gerados : " + certificados.size());
        info("Aproveitamento       : " + aprov.getStatus());
        info("Grupo                : " + grupo.getNome());
        info("Membros ativos       : " + grupoService.listarMembrosPorGrupo(grupo.getId()).size());
        // variáveis do menu
        boolean desativarMenu = false;
        int opcao = -1;

        Scanner scanner = new Scanner(System.in);

        // inicialização dos serviços
        AproveitamentoService aprovService = new AproveitamentoService();
        CertificadoService certificadoService = new CertificadoService();
        GrupoService grupoService = new GrupoService();
        InscricaoService inscricaoService = new InscricaoService();
        OportunidadeService oportunidadeService = new OportunidadeService(inscricaoService, certificadoService);
        PPCService ppcService = new PPCService();
        UsuarioService usuarioService = new UsuarioService();

        // inicialização dos menu
        Administrador admin = new Administrador("Leticia", "oi@gmail.com", "senha");
        MenuAdmin menuAdmin = new MenuAdmin(usuarioService, ppcService, admin);

        while (!desativarMenu) {
            System.out.println("============================");
            System.out.println("SISTEMA DE EXTENSÃO");
            System.out.println("============================");

            System.out.println();

            System.out.println("BEM VINDO");
            System.out.println("[1] Entrar como discente");
            System.out.println("[1] Entrar como discente diretor");
            System.out.println("[3] Entrar como docente");
            System.out.println("[4] Entrar como coordenador");
            System.out.println("[5] Entrar como administrador");
            System.out.println("[0] Sair do sistema");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    System.out.println("Entrando como discente. . .");
                    MenuDiscente menuDiscente = new MenuDiscente(, inscricaoService);
                    break;

                case 2:
                    System.out.println("Entrando como discente diretor. . .");
                    MenuDD menuDD = new MenuDD(, grupoService, inscricaoService, oportunidadeService);
                    break;

                case 3:
                    System.out.println("Entrando como docente. . .");
                    MenuDocente menuDocente = new MenuDocente(, grupoService, inscricaoService, oportunidadeService);
                    break;

                case 4:
                    System.out.println("Entrando como coordenador. . .");
                    MenuCoordenador menuCoordenador = new MenuCoordenador(, grupoService, inscricaoService,
                            oportunidadeService, ppcService);
                    break;

                case 5:
                    System.out.println("Entrando como administrador. . .");
                    menuAdmin.executar();
                    break;

                case 0:
                    desativarMenu = true;
                    System.out.println("Saíndo do sistema. . .");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

            scanner.close();

        }
    }
}