package br.ufma.extensao.menus;

import br.ufma.extensao.entidades.Curso;
import br.ufma.extensao.entidades.Usuario;
import br.ufma.extensao.enums.CargoCoordenador;
import br.ufma.extensao.servicos.*;

public class MenuAdmin extends Menu {
    private final Usuario admin;
    private final Curso curso;

    public MenuAdmin(AproveitamentoService aproveitamentoService, CertificadoService certificadoService,
                     GrupoService grupoService, InscricaoService inscricaoService,
                     OportunidadeService oportunidadeService, UsuarioService usuarioService, PPCService ppcService,
                     Usuario admin, Curso curso) {
        super(aproveitamentoService, certificadoService, grupoService, inscricaoService, oportunidadeService,
                usuarioService, ppcService);
        this.admin = admin;
        this.curso = curso;
    }

    @Override
    public void imprimir() {
        System.out.println("[1] Cadastrar novo usuário");
        System.out.println("[2] Cadastrar novo grupo");
        System.out.println("[3] Cadastrar novo PPC");
        /*System.out.println("[4] Cadastrar nova UCE");*/
        System.out.println("[4] Desativar conta");
        System.out.println("[0] Logout");
    }

    @Override
    public void executar() {
        boolean logout = false;
        int opcao;
        int tipo;

        while (!logout) {
            imprimir();
            opcao = scanner.nextInt();
            limparBuffer(scanner);

            switch (opcao) {
                case 1:
                    System.out.println("Que tipo de usuário você deseja cadastrar?");
                    System.out.println("[1] Discente\n[2] Docente\n[3] Coordenador");
                    tipo = scanner.nextInt();
                    limparBuffer(scanner);
                    cadastro(tipo);
                    break;

                case 2:
                    System.out.println("Digite o nome do grupo:");
                    String nome = scanner.nextLine();

                    System.out.println("Digite a descrição do grupo:");
                    String descricao = scanner.nextLine();

                    System.out.println("Digite o email:");
                    String email = scanner.nextLine();

                    System.out.println("Digite o ID do discente responsável:");
                    String id = scanner.nextLine();

                    grupoService.criar(nome, descricao, email, id);
                    break;

                case 3:
                    System.out.println("Digite a versão do PPC:");
                    String versao = scanner.nextLine();

                    System.out.println("Digite a carga horária total do PPC:");
                    Double cargaH = scanner.nextDouble();
                    limparBuffer(scanner);

                    ppcService.criarPPC(admin, curso, versao, cargaH);

                    break;

                case 4:
                    System.out.println("Que tipo de conta você?");
                    System.out.println("[1] Discente\n[2] Docente\n[3] Coordenador");
                    tipo = scanner.nextInt();
                    limparBuffer(scanner);

                    System.out.println("Digite o identificador (matrícula/SIAPE) da conta:");
                    String identificador = scanner.nextLine();

                    String idConta = switch (tipo) {
                        case 1 -> "DIS" + identificador;
                        case 2 -> "DOC" + identificador;
                        case 3 -> "COR" + identificador;
                        default -> {
                            System.out.println("Tipo de conta inválida!");
                            yield null;
                        }
                    };

                    if (idConta != null) {
                        Usuario u = usuarioService.buscarPorId(idConta);
                        usuarioService.anonimizar(admin, u);
                    }

                    break;

                case 0:
                    logout = true;
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    private void cadastro(int tipo) {
        System.out.println("Digite o nome:");
        String nome = scanner.nextLine();

        System.out.println("Digite o email:");
        String email = scanner.nextLine();

        String identificador;

        switch (tipo) {
            case 1:
                System.out.println("Digite a matrícula:");
                identificador = scanner.nextLine();

                System.out.println("Digite o semestre atual:");
                int semestre = scanner.nextInt();
                limparBuffer(scanner);
                // senha básica... pode ser mudada depois
                usuarioService.cadastrarDiscente(nome, email, "senha", identificador, semestre, curso);
                break;

            case 2:
                System.out.println("Digite o SIAPE:");
                identificador = scanner.nextLine();
                usuarioService.cadastrarDocente(admin, nome, email, "senha", identificador);
                break;

            case 3:
                System.out.println("Digite o SIAPE:");
                identificador = scanner.nextLine();

                System.out.println("Digite o cargo:");
                String cargo = scanner.nextLine().toUpperCase();

                try {
                    CargoCoordenador cCoordenador = CargoCoordenador.valueOf(cargo);
                    usuarioService.cadastrarCoordenador(admin, nome, email, "senha", identificador, cCoordenador);
                } catch (IllegalArgumentException e) {
                    System.out.println("Cargo inexistente!");
                }

                break;

            default:
                System.out.println("Opção inválida!");
        }
    }
}
