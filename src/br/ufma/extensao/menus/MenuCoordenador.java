package br.ufma.extensao.menus;

import br.ufma.extensao.entidades.Coordenador;
import br.ufma.extensao.entidades.Curso;
import br.ufma.extensao.entidades.Oportunidade;
import br.ufma.extensao.servicos.*;

import java.util.Scanner;

public class MenuCoordenador extends Menu {
    private final Coordenador coordenador;
    private final MenuExtra menuExtra;
    private final Curso curso;

    public MenuCoordenador(AproveitamentoService aproveitamentoService, CertificadoService certificadoService,
                           GrupoService grupoService, InscricaoService inscricaoService,
                           OportunidadeService oportunidadeService, UsuarioService usuarioService,
                           PPCService ppcService, Scanner scanner, Coordenador coordenador, MenuExtra menuExtra,
                           Curso curso) {
        super(aproveitamentoService, certificadoService, grupoService, inscricaoService, oportunidadeService,
                usuarioService, ppcService, scanner);
        this.coordenador = coordenador;
        this.menuExtra = menuExtra;
        this.curso = curso;
    }

    @Override
    public void imprimir() {
        System.out.println("[1] Criar grupo");
        System.out.println("[2] Gerenciar solicitações");
        System.out.println("[3] Visualizar solicitações");
        System.out.println("[4] Cadastrar PPC");
        //System.out.println("[5] Cadastro UCE");
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
                    menuExtra.gerenciarInscricao(tipo, coordenador);
                    break;

                case 3:
                    System.out.println("Digite o ID da oportunidade:");
                    String idOportunidade = scanner.nextLine();

                    Oportunidade op = oportunidadeService.buscarOportunidadePorId(idOportunidade);

                    if (op == null) {
                        System.out.println("Oportunidade não foi achada!");
                    } else {
                        System.out.println(inscricaoService.listarFilaEspera(op));
                    }

                    break;

                case 4:
                    System.out.println("Digite a versão do PPC:");
                    String versao = scanner.nextLine();

                    System.out.println("Digite a carga horária total do PPC:");
                    Double cargaH = scanner.nextDouble();
                    limparBuffer(scanner);

                    ppcService.criarPPC(coordenador, curso, versao, cargaH);

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
