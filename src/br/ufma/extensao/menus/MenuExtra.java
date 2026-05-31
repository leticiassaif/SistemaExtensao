package br.ufma.extensao.menus;

// serve para oportunidades e grupo!

import br.ufma.extensao.entidades.Oportunidade;
import br.ufma.extensao.entidades.Usuario;
import br.ufma.extensao.enums.Modalidade;
import br.ufma.extensao.enums.TipoOportunidade;
import br.ufma.extensao.servicos.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MenuExtra {
    private final GrupoService grupoService;
    private final InscricaoService inscricaoService;
    private final OportunidadeService oportunidadeService;
    private final Scanner scanner;
    private static final DateTimeFormatter formato = DateTimeFormatter.ISO_LOCAL_DATE;

    public MenuExtra(GrupoService grupoService, InscricaoService inscricaoService,
                     OportunidadeService oportunidadeService, Scanner scanner) {
        this.grupoService = grupoService;
        this.inscricaoService = inscricaoService;
        this.oportunidadeService = oportunidadeService;
        this.scanner = scanner;
    }

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

    public void criarOportunidade(Usuario solicitante) {
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

        System.out.println("Digite a data do início (YYYY-MM-DD):");
        String dInicio = scanner.nextLine();

        System.out.println("Digite a data do fim (YYYY-MM-DD):");
        String dFim = scanner.nextLine();

        LocalDate inicio = formataData(dInicio);
        LocalDate fim = formataData(dFim);

        if (inicio == null || fim == null) {
            System.out.println("Datas inválidas!");
            return;
        }

        TipoOportunidade tipoOportunidade;
        Modalidade modalidade;

        try {
            tipoOportunidade = TipoOportunidade.valueOf(tipo.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo inexistente!");
            return;
        }

        try {
            modalidade = Modalidade.valueOf(modal);
            oportunidadeService.criarOportunidade(titulo, descricao, tipoOportunidade, modalidade, cargaH, vagas,
                    id, solicitante, inicio, fim);
        } catch (IllegalArgumentException e) {
            System.out.println("Modalidade inexistente!");
        }
    }

    public void encerrarOportunidade() {
        System.out.println("Digite o ID da oportunidade:");
        String id = scanner.nextLine();

        oportunidadeService.encerrarOportunidade(id);
    }

    public void gerenciarInscricao(int tipo, Usuario solicitante) {
        String idInscricao;
        String idOportunidade;
        Oportunidade op;

        System.out.println("Digite o ID da oportunidade:");
        idOportunidade = scanner.nextLine();

        op = oportunidadeService.buscarOportunidadePorId(idOportunidade);

        if (op == null) {
            System.out.println("ID da oportunidade inválido!");
            return;
        }

        System.out.println("Digite o ID da inscrição:");
        idInscricao = scanner.nextLine();

        switch (tipo) {
            case 1:
                inscricaoService.aprovar(idInscricao, op, solicitante);
                break;

            case 2:
                System.out.println("Digite o motivo da rejeição:");
                String motivo = scanner.nextLine();
                inscricaoService.rejeitarRemoverDiscente(idInscricao, motivo, op, solicitante);
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

    static LocalDate formataData(String d) {
        try {
            return LocalDate.parse(d, formato);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
