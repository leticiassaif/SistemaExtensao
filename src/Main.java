import br.ufma.extensao.enums.*;
import br.ufma.extensao.menus.MenuAdmin;
import br.ufma.extensao.menus.MenuDD;
import br.ufma.extensao.menus.MenuDiscente;
import br.ufma.extensao.servicos.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // variáveis do menu
        boolean desativarMenu = false;
        boolean logout = false;
        int opcao = -1;
        int opcaoEspecifica = -1;

        // variáveis dos dados
        String nome;
        String email;
        String identificador;
        String descricao;
        String motivacao;
        String cargo;
        int semestreAtual;
        CargoCoordenador cCoordenador;

        Scanner scanner = new Scanner(System.in);

        // inicialização dos serviços
        AproveitamentoService aprovService = new AproveitamentoService();
        CertificadoService certificadoService = new CertificadoService();
        GrupoService grupoService = new GrupoService();
        InscricaoService inscricaoService = new InscricaoService();
        OportunidadeService oportunidadeService = new OportunidadeService();
        UsuarioService usuarioService = new UsuarioService();

        // jogar isso no menu

        while (!desativarMenu) {
            System.out.println("============================");
            System.out.println("SISTEMA DE EXTENSÃO");
            System.out.println("============================");

            System.out.println();

            System.out.println("BEM VINDO");
            System.out.println("[1] Entrar como discente");
            System.out.println("[1] Entrar como discente diretor");
            System.out.println("[3] Entrar como docente");
            System.out.println("[4] Entrar como coordenador");
            System.out.println("[5] Entrar como administrador");
            System.out.println("[0] Sair do sistema");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    System.out.println("Entrando como discente. . .");
                    break;

                case 2:
                    System.out.println("Entrando como discente diretor. . .");
                    break;


                case 3:
                    System.out.println("Entrando como docente. . .");
                    break;

                case 4:
                    break;

                case 5:
                    System.out.println("Entrando como administrador. . .");
                    break;

                case 0:
                    desativarMenu = true;
                    System.out.println("Saíndo do sistema. . .");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

            scanner.close();

        }
    }
}