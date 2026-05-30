package br.ufma.extensao.menus;

import br.ufma.extensao.entidades.Coordenador;
import br.ufma.extensao.servicos.GrupoService;
import br.ufma.extensao.servicos.InscricaoService;
import br.ufma.extensao.servicos.OportunidadeService;

public class MenuCoordenador extends Menu {
    private final Coordenador coordenador;
    private final MenuExtra menuExtra;

    public MenuCoordenador(Coordenador coordenador, GrupoService grupoService, InscricaoService inscricaoService,
                           OportunidadeService oportunidadeService) {
        this.coordenador = coordenador;
        this.menuExtra = new MenuExtra(grupoService, inscricaoService, oportunidadeService);
    }

    @Override
    public void imprimir() {
        System.out.println("[1] Criar grupo");
        System.out.println("[2] Gerenciar solicitações");
        System.out.println("[3] Visualizar solicitações");
        System.out.println("[4] Cadastrar PPC");
        System.out.println("[5] Cadastro UCE");
        System.out.println("[0] Logout");
    }

    @Override
    public void executar() {
        boolean logout = false;
        int opcao;

        while (!logout) {
            imprimir();
            opcao = scanner.nextInt();
            limparBuffer(scanner);

            switch (opcao) {
                case 1:
                    menuExtra.criarGrupo();
                    break;

                case 2:
                    System.out.println("Você deseja\n[1]Aprovar\n[2]");
                    int tipo = scanner.nextInt();
                    limparBuffer(scanner);
                    menuExtra.gerenciarInscricao(tipo);
                    break;

                case 3:
                    // checar com as meninas
                    break;

                case 4:
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

                case 5:
                    // falta função
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
}
