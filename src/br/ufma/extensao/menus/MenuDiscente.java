package br.ufma.extensao.menus;

import br.ufma.extensao.entidades.Discente;
import br.ufma.extensao.servicos.*;

public class MenuDiscente extends Menu {
    private final Discente discente;
    private final InscricaoService inscricaoService;

    public MenuDiscente(Discente discente, InscricaoService inscricaoService) {
        this.discente = discente;
        this.inscricaoService = inscricaoService;
    }

    @Override
    public void imprimir() {
        System.out.println("[1] Inscrever-se em oportunidade");
        System.out.println("[2] Cancelar inscrição");
        System.out.println("[3] Listar inscrições");
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
                    // fazer uma busca para achar a oportunidade
                    System.out.println("Digite a motivação:");
                    String motivacao = scanner.next();

                    inscricaoService.inscrever(discente, , motivacao);
                    break;

                case 2:
                    // olhar como pegaria esse id?
                    System.out.println("oi");
                    String id = scanner.nextLine();
                    // deveria ser só discente ou usuário mesmo?
                    inscricaoService.cancelar(id, discente);
                    break;

                case 3:
                    inscricaoService.listarPorDiscente(discente);
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
