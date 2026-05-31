package br.ufma.extensao.menus;

import br.ufma.extensao.entidades.Discente;
import br.ufma.extensao.entidades.DiscenteDiretor;
import br.ufma.extensao.entidades.Usuario;
import br.ufma.extensao.servicos.GrupoService;
import br.ufma.extensao.servicos.InscricaoService;
import br.ufma.extensao.servicos.OportunidadeService;

public class MenuDD extends Menu {
    private final DiscenteDiretor diretor;
    private final InscricaoService inscricaoService;
    private final MenuExtra menuExtra;

    public MenuDD(DiscenteDiretor diretor, GrupoService grupoService, InscricaoService inscricaoService,
                  OportunidadeService oportunidadeService) {
        this.diretor = diretor;
        this.inscricaoService = inscricaoService;
        this.menuExtra = new MenuExtra(grupoService, inscricaoService, oportunidadeService);
    }

    @Override
    public void imprimir() {
        System.out.println("[1] Inscrever-se em oportunidade");
        System.out.println("[2] Cancelar inscrição");
        System.out.println("[3] Listar inscrições");
        System.out.println("[4] Gerenciar inscrições");
        System.out.println("[5] Criar grupo");
        System.out.println("[6] Criar oportunidade");
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
                    // fazer uma busca para achar a oportunidade
                    System.out.println("Digite a motivação:");
                    String motivacao = scanner.next();

                    inscricaoService.inscrever(diretor, , motivacao);
                    break;

                case 2:
                    // olhar como pegaria esse id?
                    System.out.println("oi");
                    String id = scanner.nextLine();
                    inscricaoService.cancelar(id, diretor);
                    break;

                case 3:
                    inscricaoService.listarPorDiscente(diretor);
                    break;

                case 4:
                    System.out.println("Você deseja\n[1]Aprovar\n[2]");
                    tipo = scanner.nextInt();
                    limparBuffer(scanner);
                    menuExtra.gerenciarInscricao(tipo);
                    break;

                case 5:
                    menuExtra.criarGrupo();
                    break;

                case 6:
                    menuExtra.criarOportunidade(diretor);
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
