// Importação dos pacotes corretos do projeto
import br.ufma.extensao.enums.*;
import br.ufma.extensao.entidades.*;
import br.ufma.extensao.menus.MenuAdmin;
import br.ufma.extensao.servicos.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // variáveis do menu
        boolean desativarMenu = false;
        boolean logout = false;
        int opcao = -1;
        int opcaoEspecifica = -1;

        // variáveis dos dados
        String nome;
        String email;
        String identificador;
        String descricao;
        String cargo;

        CargoCoordenador cCoordenador;

        Scanner scanner = new Scanner(System.in);

        // inicialização dos serviços
        UsuarioService usuarioService       = new UsuarioService();
        OportunidadeService oportunidadeService = new OportunidadeService();
        InscricaoService inscricaoService   = new InscricaoService();
        CertificadoService certificadoService = new CertificadoService();
        AproveitamentoService aprovService  = new AproveitamentoService();
        GrupoService grupoService           = new GrupoService();

        // inicialização dos menus
        MenuAdmin menuAdmin = new MenuAdmin();

        System.out.println("   Todos os serviços instanciados.\n");

        while (desativarMenu == false) {
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

                // logado como discente
                case 1:
                    System.out.println("Entrando como discente. . .");

                    while (logout == false) {}

                case 2:
                    System.out.println("Entrando como discente diretor. . .");

                    while (logout == false) {}


                case 3:
                    System.out.println("Entrando como docente. . .");

                    while (logout == false) {}

                case 4:

                case 5:
                    System.out.println("Entrando como administrador. . .");

                    while (logout == false) {
                        menuAdmin.imprimirEspecifico();
                        opcaoEspecifica = scanner.nextInt();

                        switch (opcaoEspecifica) {
                            case 1:
                                System.out.println("Digite o nome do coodernador:");
                                nome = scanner.next();
                                System.out.println("Digite o email do coodernador:");
                                email = scanner.next();
                                System.out.println("Digite o siape do coodernador:");
                                identificador = scanner.next();
                                System.out.println("Digite o cargo do coodernador:");
                                cargo = scanner.next().toUpperCase();

                                try {
                                    cCoordenador = CargoCoordenador.valueOf(cargo);
                                    usuarioService.cadastrarCoordenador(nome, email, "coordenador", identificador, cCoordenador);
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Cargo inexistente!");
                                }

                            case 2:
                                System.out.println("Digite o nome do docente:");
                                nome = scanner.next();
                                System.out.println("Digite o email do docente:");
                                email = scanner.next();
                                System.out.println("Digite o siape do docente:");
                                identificador = scanner.next();

                                usuarioService.cadastrarDocente(nome, email, "docente", identificador);

                            case 3:
                                System.out.println("Digite o nome do grupo:");
                                nome = scanner.next();
                                System.out.println("Digite a descrição do grupo:");
                                descricao = scanner.next();
                                System.out.println("Digite o email:");
                                email = scanner.next();
                                System.out.println("Digite o siape do docente:");
                                identificador = scanner.next();

                                grupoService.criar(nome, descricao, email, identificador);

                            case 4:
                                // novo ppc

                            case 5:
                                // nova uce

                            case 6:
                                // desativar conta

                            case 0:
                                logout = true;
                                System.out.println("Logging out. . .");

                            default:
                                System.out.println("Opção inválida!");
                        }
                    }

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

    /*
    // PASSO 4 — Criar uma oportunidade e publicá-la
        System.out.println("Criando e publicando oportunidade:");

        // criarOportunidade requer: titulo, descricao, tipo, modalidade,
        //   cargaHoraria, vagas, responsavelId (Long), autor, inicio, fim
        Oportunidade oportunidade = oportunidadeService.criarOportunidade(
                "Monitoria de Programação Orientada a Objetos",
                "Auxílio a alunos na disciplina de LP2",
                TipoOportunidade.PROJETO,
                Modalidade.PRESENCIAL,
                60,
                5,
                null,           // responsavelId (Long) — não obrigatório nesta etapa
                docente,
                LocalDate.now(),
                LocalDate.now().plusMonths(6)
        );
        System.out.println("   Oportunidade criada  : " + oportunidade.getTitulo()
                + " | Status: " + oportunidade.getStatus());

        // Publica a oportunidade (docente → status ABERTA diretamente)
        oportunidadeService.publicarOportunidade(oportunidade.getId(), docente);
        System.out.println("   Oportunidade publicada. Status: " + oportunidade.getStatus() + "\n");

    // PASSO 5 — Inscrever o discente e aprovar a inscrição
        System.out.println("Inscrevendo discente e aprovando inscrição:");

        // inscrever requer: discente, oportunidade, motivacao
        Inscricao inscricao = inscricaoService.inscrever(
                discente,
                oportunidade,
                "reforçar meus conhecimentos"
        );
        System.out.println("   Inscrição realizada: " + discente.getNome()
                + " → " + oportunidade.getTitulo());

        // Aprova a inscrição (docente tem permissão)
        inscricaoService.aprovar(inscricao.getId(), docente);
        System.out.println("   Inscrição aprovada Status: " + StatusInscricao.APROVADA + "\n");

    // PASSO 6 — Iniciar execução da oportunidade e encerrar
        System.out.println("Iniciando e encerrando execução da oportunidade:");

        oportunidadeService.iniciarExecucao(oportunidade.getId());
        System.out.println("   Oportunidade em andamento. Status: " + oportunidade.getStatus());

        oportunidadeService.encerrarOportunidade(oportunidade.getId());
        System.out.println("   Oportunidade encerrada. Status: " + oportunidade.getStatus() + "\n");

    // PASSO 7 — Gerar certificado para o discente
        System.out.println("Gerando certificado para o discente:");

        // gerar requer: discente, oportunidade, cargaHoraria, dataEmissao
        Certificado certificado = certificadoService.gerar(
                discente,
                oportunidade,
                oportunidade.getCargaHoraria(),
                LocalDate.now()
        );
        System.out.println("   Certificado gerado para: " + discente.getNome()
                + " | Oportunidade: " + oportunidade.getTitulo() + "\n");

    // PASSO 8 — Buscar o certificado pelo código de autenticidade e imprimir
        System.out.println("Buscando certificado pelo código de autenticidade:");

        Certificado certificadoEncontrado = certificadoService.buscar(certificado.getCodigoAutenticidade());
        System.out.println("   Certificado encontrado: " + certificadoEncontrado.getCodigoAutenticidade());
        System.out.println("   Discente     : " + certificadoEncontrado.getDiscente().getNome());
        System.out.println("   Oportunidade : " + certificadoEncontrado.getOportunidade().getTitulo());
        System.out.println("   Carga horária: " + certificadoEncontrado.getCargaHorariaCumprida() + "h");
        System.out.println("   Data emissão : " + certificadoEncontrado.getDataEmissao() + "\n");

    // PASSO 9 — Submeter solicitação de aproveitamento
        System.out.println("Submetendo solicitação de aproveitamento:");

        // submeter requer: discenteId, descricao, cargaHorariaPleiteada
        Aproveitamento aproveitamento = aprovService.submeter(
                discente.getId(),
                "Participação em workshop de Java fora da universidade",
                20.0
        );
        System.out.println("   Solicitação submetida por: " + discente.getNome());
        System.out.println("   Status: " + aproveitamento.getStatus() + "\n");

    // PASSO 10 — Indeferir, reenviar e aprovar o aproveitamento
        System.out.println("Indeferindo solicitação com parecer:");

        // Indeferir requer permissão de COORDENADOR
        aprovService.indeferir(aproveitamento.getId(), coordenador);
        System.out.println("   Solicitação indeferida.");
        System.out.println("   Status: " + aproveitamento.getStatus());

        // Discente reenvia a solicitação após correção
        aprovService.reenviar(aproveitamento.getId());
        System.out.println("   Discente reenviou com novos dados e documentação corrigida.");
        System.out.println("   Status: " + aproveitamento.getStatus());

        // Coordenador aprova após análise
        aprovService.aprovar(aproveitamento.getId(), coordenador, aproveitamento.getCargaHorariaPleiteada());
        System.out.println("   Solicitação aprovada pelo avaliador: " + coordenador.getNome());
        System.out.println("   Status: " + aproveitamento.getStatus() + "\n");

    // PASSO 11 — Criar grupo e adicionar o discente como DIRETOR
        System.out.println("Criando grupo e adicionando discente como DIRETOR:");

        // criar requer: nome, descricao, email, docenteResponsavelId
        Grupo grupo = grupoService.criar(
                "Diretório Acadêmico de Computação",
                "Representação estudantil do curso de CC",
                "dac@discente.ufma.br",
                docente.getId()
        );
        System.out.println("   Grupo criado: " + grupo.getNome());

        // Cria um DiscenteDiretor com os dados do discente já cadastrado
        // Não há mais o "Cast" de papel.DISCENTE_DIRETOR, isso já está claro em DiscenteDiretor
        DiscenteDiretor diretor = new DiscenteDiretor(
                discente.getNome(),
                discente.getEmail(),
                discente.getSenha(),
                discente.getMatricula(),
                discente.getSemestreAtual(),
                grupo,
                "Diretor"
        );

        // Adiciona o diretor como membro do grupo via GrupoService
        grupoService.adicionarMembro(grupo.getId(), diretor.getId(), Cargo.DIRETOR);
        System.out.println("   " + diretor.getNome()
                + " adicionado como DIRETOR."
                + " | Cargo: " + diretor.getCargo()
                + " | Papel: " + diretor.getPapel().getDescricao() + "\n");

    // PASSO 12 — Imprimir o resultado final de cada operação
        System.out.println("Resultado Final");
        System.out.println("  Docente cadastrado    : " + docente.getNome());
        System.out.println("  Discente cadastrado   : " + discente.getNome());
        System.out.println("  Coordenador cadastrado: " + coordenador.getNome());
        System.out.println("  Oportunidade          : " + oportunidade.getTitulo()
                + " | Status: " + oportunidade.getStatus());
        System.out.println("  Inscrição             : " + discente.getNome()
                + " → " + oportunidade.getTitulo()
                + " | Status: " + inscricao.getStatus());
        System.out.println("  Certificado emitido   : " + discente.getNome()
                + " | " + certificado.getCargaHorariaCumprida() + "h"
                + " | Código: " + certificado.getCodigoAutenticidade());
        System.out.println("  Aproveitamento        : " + discente.getNome()
                + " | Status: " + aproveitamento.getStatus());
        System.out.println("  Grupo/Diretor         : " + diretor.getNome()
                + " | Cargo: " + diretor.getCargo());
    }*/
}