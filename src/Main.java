// Importação de todos os ARQUIVOS.
import enums.*;
import model.*;
import service.*;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        System.out.println("Gestão de Atividades Complementares");
//PASSO 1 - INSTANCIAR TODOS OS SERVIÇOS

        System.out.println("Instanciando todos os serviços:");
        // Cria o service responsável por gerenciar usuários (discentes, docentes, admins)
        UsuarioService usuarioService = new UsuarioService();
        OportunidadeService oportunidadeService = new OportunidadeService();
        InscricaoService inscricaoService = new InscricaoService();
        CertificadoService certificadoService = new CertificadoService();
        AproveitamentoService aprovService = new AproveitamentoService();
        GrupoService grupoService = new GrupoService();

        // Confirma que todos os serviços foram criados com sucesso
        System.out.println(" Todos os servicos estao instanciados. \n");

// PASSO 2 — Cadastrar um Docente e um Discente
        System.out.println("Cadastrando Docente e Discente:"); //cadastra DOCENTE

        // Chama o método cadastrarDocente do UsuarioService passando nome, email, senha e SIAPE
        // O método já define o papel como DOCENTE e o departamento como "Departamento de Informática"
        Docente docente = usuarioService.cadastrarDocente(
                "Prof. Carlos Silva",          // Nome do docente
                "carlos.silva@docente.ufma.br",// E-mail institucional
                "senha123",                    // Senha
                "1234567"                      // IAPE
        );
        System.out.println("   Docente cadastrado : " + docente.getNome() //impreme os dados
                + " | SIAPE: " + docente.getSiape()
                + " | Depto: " + docente.getDepartamento());

        // Chama o método cadastrarDiscente do UsuarioService passando nome, email, senha, matrícula e semestre
        // O método já define o papel como DISCENTE e associa ao curso de Ciência da Computação
        Discente discente = usuarioService.cadastrarDiscente( //cadastra DISCENTE
                "Ana Souza",                   // Nome do discente
                "ana.souza@discente.ufma.br",           // E-mail institucional
                "senha456",                    // Senha
                "20230001",                    // Matrícula do discente
                3                             // Semestre atual
        );
        System.out.println("   Discente cadastrado: " + discente.getNome()
                + " | Matrícula: " + discente.getMatricula()
                + " | Semestre: " + discente.getSemestreAtual()
                + " | Curso: " + discente.getCurso().getNome() + "\n");

// PASSO 3 — Cadastrar um Coordenador usando UsuarioService
        System.out.println("Cadastrando Coordenador...");
        // Coordenador é cadastrado como Admin pois tem papel administrativo no sistema

        Usuario coordenador = usuarioService.cadastrarAdmin(
                "Coord. Mariana Lima",         // Nome do coordenador
                "mariana.lima@ufma.br",        // E-mail institucional
                "senha789"                     // Senha
        );

        // Imprime os dados do coordenador cadastrado — getDescricao() retorna "Administrador"
        System.out.println("   Coordenador cadastrado: " + coordenador.getNome()
                + " | Papel: " + coordenador.getPapel().getDescricao() + "\n");

// PASSO 4 — Criar uma oportunidade e publicá-la
        System.out.println("Criando e publicando oportunidade:");

        Oportunidade oportunidade = new Oportunidade();
        oportunidade.setTitulo("Monitoria de Programação Orientada a Objetos");
        oportunidade.setDescricao("Auxílio a alunos na disciplina de LP2");
        oportunidade.setTipo(TipoOportunidade.MONITORIA);
        oportunidade.setModalidade(Modalidade.PRESENCIAL);
        oportunidade.setCargaHoraria(60);
        oportunidade.setVagas(5);
        oportunidade.setStatus(StatusOportunidade.PENDENTE);
        oportunidade.setInicio(LocalDate.now());
        oportunidade.setFim(LocalDate.now().plusMonths(6));

        // Define o docente cadastrado como autor da oportunidade
        oportunidade.setAutor(docente);

        // Define o mesmo docente como responsável pela oportunidade
        oportunidade.setResponsavel(docente);

        // Adiciona a oportunidade na lista do OportunidadeService
        oportunidadeService.oportunidades.add(oportunidade);
        System.out.println("   Oportunidade criada : " + oportunidade.getTitulo() //status de oportunidade
                + " | Status: " + oportunidade.getStatus());
        oportunidade.setStatus(StatusOportunidade.PUBLICADA);
        System.out.println("   Oportunidade publicada Status: " + oportunidade.getStatus() + "\n");

// PASSO 5 — Inscrever o discente e aprovar a inscrição
        System.out.println("Inscrevendo discente e aprovando inscrição");
        Inscricao inscricao = new Inscricao();

        inscricaoService.inscricoes.add(inscricao);

        System.out.println("   Inscrição realizada: " + discente.getNome()
                + " → " + oportunidade.getTitulo());
        // O método aprovar() está definido na classe Inscricao
        inscricao.aprovar(LocalDate.now());

        // Imprime o status após aprovação usando o enum StatusInscricao
        System.out.println("   Inscrição aprovada! Status: " + StatusInscricao.APROVADO + "\n");

// PASSO 6 — Iniciar execução da oportunidade e encerrar
        System.out.println("Iniciando e encerrando execução da oportunidade...");

        // Muda o status da oportunidade para EM_PROGRESSO indicando que já começou
        oportunidade.setStatus(StatusOportunidade.EM_PROGRESSO);

        System.out.println("Oportunidade em andamento. \nStatus: " + oportunidade.getStatus());
        oportunidade.fecharInscricoes(); //fechar inscrições
        oportunidade.setStatus(StatusOportunidade.ENCERRADA); //oportunidade concluida
        System.out.println("   Oportunidade encerrada\n. Status: " + oportunidade.getStatus() + "\n");

// PASSO 7 — Gerar certificado para o discente
        System.out.println("Gerando certificado para o discente:");
        Certificado certificado = new Certificado();
        certificadoService.certificados.add(certificado);

        System.out.println("   Certificado gerado para: " + discente.getNome()
                + " | Oportunidade: " + oportunidade.getTitulo() + "\n");

// PASSO 8 — Buscar o certificado pelo código e imprimir
        System.out.println("Buscando certificado pelo código:");

        Certificado certificadoEncontrado = certificadoService.certificados.get(0); //primeiro elemento da lista
        System.out.println("   Certificado encontrado");
        System.out.println("   Discente     : " + discente.getNome());
        System.out.println("   Oportunidade : " + oportunidade.getTitulo());
        System.out.println("   Carga horária: " + oportunidade.getCargaHoraria() + "h");
        System.out.println("   Data emissão : " + LocalDate.now() + "\n");

// PASSO 9: Submetendo solicitação de aproveitamento
        System.out.println("Submetendo solicitação de aproveitamento: ");

        Aproveitamento aproveitamento = new Aproveitamento();
        aprovService.aproveitamentos.add(aproveitamento);

        System.out.println("   Solicitação submetida por: " + discente.getNome());
        System.out.println("   Status: " + StatusAproveitamento.PENDENTE + "\n");
// PASSO 10 — Indeferir, reenviar e aprovar o aproveitamento
        System.out.println("Indeferindo solicitação com parecer:");

        // Simula o indeferimento da solicitação com um motivo/parecer
        System.out.println("   Solicitação indeferida.");
        System.out.println("   Motivo: Documentação incompleta.");
        System.out.println("   Status: " + StatusAproveitamento.REJEITADO);

        // Simula o reenvio pelo discente com novos dados e documentação corrigida
        System.out.println("   Discente reenviou com novos dados e documentação corrigida.");
        System.out.println("   Status: " + StatusAproveitamento.PENDENTE);

        // Simula a aprovação pelo avaliador (docente) após análise dos novos dados
        System.out.println("   Solicitação aprovada pelo avaliador: " + docente.getNome());
        System.out.println("   Status: " + StatusAproveitamento.APROVADO + "\n");
// PASSO 11 — Criar grupo e adicionar o discente como DIRETO
        System.out.println("Criando grupo e adicionando discente como DIRETOR");

        Grupo grupo = new Grupo();
        grupoService.grupos.add(grupo);

        System.out.println("Grupo criado:");

        // Cria um DiscenteDiretor aproveitando os dados do discente já cadastrado
        // DiscenteDiretor estende Discente, então recebe os mesmos dados básicos + grupo e cargo
        DiscenteDiretor diretor = new DiscenteDiretor(
                discente.getNome(),
                discente.getEmail(),
                discente.getSenha(),
                Papel.DISCENTE_DIRETOR,
                discente.getMatricula(),
                discente.getSemestreAtual(),
                grupo,
                "Diretor"
        );

        // Adiciona o diretor como membro do grupo usando o método addMembro()
        grupo.addMembro(diretor);

        System.out.println("   " + diretor.getNome()
                + " adicionado como DIRETOR."
                + " | Cargo: " + diretor.getCargo()
                + " | Papel: " + diretor.getPapel().getDescricao() + "\n");

// PASSO 12 — Imprimir o resultado de cada operação
        System.out.println("Resultado final:")
        System.out.println("  Docente cadastrado    : " + docente.getNome());
        System.out.println("  Discente cadastrado   : " + discente.getNome());


        System.out.println("  Coordenador cadastrado: " + coordenador.getNome());


        System.out.println("  Oportunidade          : " + oportunidade.getTitulo()
                + " | Status: " + oportunidade.getStatus());


        System.out.println("  Inscrição             : " + discente.getNome()
                + " → " + oportunidade.getTitulo()
                + " | Status: " + StatusInscricao.APROVADO);

        System.out.println("  Certificado emitido   : " + discente.getNome()
                + " | " + oportunidade.getCargaHoraria() + "h");

        System.out.println("  Aproveitamento        : " + discente.getNome()
                + " | Status: " + StatusAproveitamento.APROVADO);

        System.out.println("  Grupo/Diretor         : " + diretor.getNome()
                + " | Cargo: " + diretor.getCargo());

    }
}