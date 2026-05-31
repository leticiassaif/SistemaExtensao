package br.ufma.extensao.menus;

import br.ufma.extensao.entidades.DiscenteDiretor;
import br.ufma.extensao.entidades.Oportunidade;
import br.ufma.extensao.servicos.*;

public class MenuDD extends Menu {
    private final DiscenteDiretor diretor;
    private final MenuExtra menuExtra;


    public MenuDD(AproveitamentoService aproveitamentoService, CertificadoService certificadoService,
                  GrupoService grupoService, InscricaoService inscricaoService, OportunidadeService oportunidadeService,
                  UsuarioService usuarioService, PPCService ppcService, DiscenteDiretor diretor, MenuExtra menuExtra) {
        super(aproveitamentoService, certificadoService, grupoService, inscricaoService, oportunidadeService,
                usuarioService, ppcService);
        this.diretor = diretor;
        this.menuExtra = menuExtra;
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
        String idOportunidade;
        Oportunidade op;

        while (!logout) {
            imprimir();
            opcao = scanner.nextInt();
            limparBuffer(scanner);

            switch (opcao) {
                case 1:
                    System.out.println("Digite o ID da oportunidade:");
                    idOportunidade = scanner.nextLine();

                    op = oportunidadeService.buscarOportunidadePorId(idOportunidade);

                    System.out.println("Digite a motivação:");
                    String motivacao = scanner.next();

                    if (op == null) {
                        System.out.println("Oportunidade não foi achada!");
                    } else {
                        inscricaoService.inscrever(diretor, op, motivacao);
                    }

                    break;

                case 2:
                    System.out.println("Digite o ID da sua inscrição:");
                    String idInscricao = scanner.nextLine();

                    System.out.println("Digite o ID da oportunidade:");
                    idOportunidade = scanner.nextLine();

                    op = oportunidadeService.buscarOportunidadePorId(idOportunidade);

                    if (op == null) {
                        System.out.println("Oportunidade não foi achada!");
                    } else {
                        inscricaoService.desistir(idInscricao, op, diretor);
                    }

                    break;

                case 3:
                    System.out.println(inscricaoService.listarPorDiscente(diretor));
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
