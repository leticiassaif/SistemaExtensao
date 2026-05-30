package br.ufma.extensao.menus;

import br.ufma.extensao.entidades.Docente;
import br.ufma.extensao.servicos.GrupoService;
import br.ufma.extensao.servicos.InscricaoService;
import br.ufma.extensao.servicos.OportunidadeService;

public class MenuDocente extends Menu {
    private final Docente docente;
    private final GrupoService grupoService;
    private final InscricaoService inscricaoService;
    private final OportunidadeService oportunidadeService;
    private final MenuExtra menuExtra;

    public MenuDocente(Docente docente, GrupoService grupoService, InscricaoService inscricaoService,
                       OportunidadeService oportunidadeService) {
        this.docente = docente;
        this.grupoService = grupoService;
        this.inscricaoService = inscricaoService;
        this.oportunidadeService = oportunidadeService;
        this.menuExtra = new MenuExtra(grupoService, inscricaoService, oportunidadeService);
    }

    @Override
    public void imprimir() {
        System.out.println("[1] Criar oportunidade");
        System.out.println("[2] Encerrar oportunidade");
        System.out.println("[3] Gerenciar inscrições");
        System.out.println("[4] Substituir participante");
        System.out.println("[5] Atribuir cargos");
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
                    menuExtra.criarOportunidade(docente);
                    break;

                case 2:
                    menuExtra.encerrarOportunidade();
                    break;

                case 3:
                    System.out.println("Você deseja\n[1]Aprovar\n[2]");
                    int tipo = scanner.nextInt();
                    limparBuffer(scanner);
                    menuExtra.gerenciarInscricao(tipo);
                    break;

                case 4:
                    // falta função
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
