package br.ufma.extensao.menus;

import br.ufma.extensao.enums.CargoCoordenador;
import br.ufma.extensao.servicos.*;

import java.util.Scanner;

public class MenuAdmin extends Menu {
    private final UsuarioService usuarioService;

    public MenuAdmin(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public void imprimir() {
        System.out.println("[1] Cadastrar novo usuário");
        System.out.println("[2] Cadastrar novo grupo");
        System.out.println("[3] Cadastrar novo PPC");
        System.out.println("[4] Cadastrar nova UCE");
        System.out.println("[5] Desativar conta");
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
                    cadastro(tipo);
                    break;

                case 2:
                    break;

                case 3:
                    System.out.println("Digite o ID do PPC:");
                    Long id = scanner.nextLong();
                    limparBuffer(scanner);

                    System.out.println("Digite a versão do PPC:");
                    int versao = scanner.nextInt();
                    limparBuffer(scanner);

                    System.out.println("Digite a carga horária do PPC:");
                    double cargaH = scanner.nextDouble();
                    limparBuffer(scanner);

                    // chamar

                    break;

                case 4:
                    // criar UCE
                    break;

                case 5:
                    // chamar busca e desativar conta
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
                usuarioService.cadastrarDiscente(nome, email, "senha", identificador, semestre);
                break;

            case 2:
                System.out.println("Digite o SIAPE:");
                identificador = scanner.nextLine();
                usuarioService.cadastrarDocente(nome, email, "senha", identificador);
                break;

            case 3:
                System.out.println("Digite o SIAPE:");
                identificador = scanner.nextLine();

                System.out.println("Digite o cargo:");
                String cargo = scanner.nextLine().toUpperCase();

                try {
                    CargoCoordenador cCoordenador = CargoCoordenador.valueOf(cargo);
                    usuarioService.cadastrarCoordenador(nome, email, "senha", identificador, cCoordenador);
                } catch (IllegalArgumentException e) {
                    System.out.println("Cargo inexistente!");
                }

                break;

            default:
                System.out.println("Opção inválida!");
        }
    }
}
