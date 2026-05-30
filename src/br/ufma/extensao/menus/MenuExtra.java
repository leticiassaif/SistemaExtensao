package br.ufma.extensao.menus;

// serve para oportunidades e grupo!

import br.ufma.extensao.entidades.Usuario;
import br.ufma.extensao.enums.Modalidade;
import br.ufma.extensao.enums.TipoOportunidade;
import br.ufma.extensao.servicos.*;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuExtra {
    private final GrupoService grupoService;
    private final InscricaoService inscricaoService;
    private final OportunidadeService oportunidadeService;
    private final Scanner scanner;

    public MenuExtra(GrupoService grupoService, InscricaoService inscricaoService,
                     OportunidadeService oportunidadeService) {
        this.grupoService = grupoService;
        this.inscricaoService = inscricaoService;
        this.oportunidadeService = oportunidadeService;
        scanner = new Scanner(System.in);
    }

    /*public void addPPC(Usuario solicitante) {
        // pode tirar no futuro
    }*/

    public void criarGrupo() {
        System.out.println("Digite o nome do grupo:");
        String nome = scanner.nextLine();

        System.out.println("Digite a descrição do grupo:");
        String descricao = scanner.nextLine();

        System.out.println("Digite o email:");
        String email = scanner.nextLine();


        System.out.println("Digite o ID do discente responsável:");
        String id = scanner.nextLine();

        grupoService.criar(nome, descricao, email, id);
    }

    public void criarOportunidade(Usuario usuario) {
        System.out.println("Digite o título da oportunidade:");
        String titulo = scanner.nextLine();

        System.out.println("Digite a descrição da oportunidade:");
        String descricao = scanner.nextLine();

        System.out.println("Digite o tipo da oportunidade:");
        String tipo = scanner.nextLine();

        System.out.println("Digite a modalidade da oportunidade:");
        String modal = scanner.nextLine();

        System.out.println("Digite a carga horária da oportunidade:");
        int cargaH = scanner.nextInt();
        limparBuffer(scanner);

        System.out.println("Digite a quantidade de vagas da oportunidade:");
        int vagas = scanner.nextInt();
        limparBuffer(scanner);

        System.out.println("Digite o ID do responsável:");
        String id = scanner.nextLine();

        // CHECAR O INPUT DO USER!!!!
        System.out.println("Digite a data do íncio (YYYY-MM-DD):");
        String dInicio = scanner.nextLine();

        System.out.println("Digite a data do fim (YYYY-MM-DD):");
        String dFim = scanner.nextLine();

        LocalDate inicio = LocalDate.parse(dInicio);
        LocalDate fim = LocalDate.parse(dFim);

        try {
            TipoOportunidade tipoOportunidade = TipoOportunidade.valueOf(tipo);
            try {
                Modalidade modalidade = Modalidade.valueOf(modal);
                oportunidadeService.criarOportunidade(titulo, descricao, tipoOportunidade, modalidade, cargaH, vagas,
                        id, usuario, inicio, fim);
            } catch (IllegalArgumentException e) {
                System.out.println("Modalidade inexistente!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo inexistente!");
        }
    }

    public void encerrarOportunidade() {
        System.out.println("Digite o ID da oportunidade:");
        String id = scanner.nextLine();

        oportunidadeService.encerrarOportunidade(id);
    }

    public void gerenciarInscricao(int tipo) {
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

    static void limparBuffer(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}
