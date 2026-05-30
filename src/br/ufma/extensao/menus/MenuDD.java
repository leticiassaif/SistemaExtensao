package br.ufma.extensao.menus;

import br.ufma.extensao.entidades.Discente;
import br.ufma.extensao.entidades.DiscenteDiretor;
import br.ufma.extensao.entidades.Usuario;
import br.ufma.extensao.servicos.InscricaoService;
import br.ufma.extensao.servicos.OportunidadeService;

public class MenuDD extends Menu {
    private final DiscenteDiretor diretor;
    private final InscricaoService inscricaoService;
    private final OportunidadeService oportunidadeService;

    public MenuDD(DiscenteDiretor diretor, InscricaoService inscricaoService, OportunidadeService oportunidadeService) {
        this.diretor = diretor;
        this.inscricaoService = inscricaoService;
        this.oportunidadeService = oportunidadeService;
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
                    // deveria ser só discente ou usuário mesmo?
                    inscricaoService.cancelar(id, diretor);
                    break;

                case 3:
                    inscricaoService.listarPorDiscente(diretor);
                    break;

                case 4:
                    System.out.println("Você deseja\n[1]Aprovar\n[2]");
                    tipo = scanner.nextInt();
                    limparBuffer(scanner);
                    gerenciarInscricao(tipo);
                    break;

                case 5:
                    // decidir como vai ser menu extra
                    break;

                case 6:
                    // decidir como vai ser menu extra
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

    private void gerenciarInscricao(int tipo) {
        String id;
        Usuario u;

        System.out.println("Digite o nome do discente:");
        // fazer a busca...

        System.out.println("Digite o ID:");
        id = scanner.nextLine();

        switch (tipo) {
            case 1:
                inscricaoService.aprovar(id, u);
                break;

            case 2:
                inscricaoService.rejeitar(id, u);
                break;

            default:
                System.out.println("Opção inválida!");
                break;

        }
    }
}
