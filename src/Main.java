import br.ufma.extensao.entidades.*;
import br.ufma.extensao.enums.*;
import br.ufma.extensao.menus.*;
import br.ufma.extensao.servicos.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // variáveis do menu
        boolean desativarMenu = false;
        int opcao = -1;

        Scanner scanner = new Scanner(System.in);

        // inicialização dos serviços
        AproveitamentoService aprovService = new AproveitamentoService();
        CertificadoService certificadoService = new CertificadoService();
        GrupoService grupoService = new GrupoService();
        InscricaoService inscricaoService = new InscricaoService();
        OportunidadeService oportunidadeService = new OportunidadeService(inscricaoService, certificadoService);
        PPCService ppcService = new PPCService();
        UsuarioService usuarioService = new UsuarioService(ppcService);

        // inicialização do curso + ppc + admin
        Administrador admin = new Administrador("Leticia", "oi@gmail.com", "admin");
        Curso curso = new Curso("123", "Ciência da Computação", "12345");
        PPC ppc = ppcService.criarPPC(admin, curso, "1", 45.5);

        MenuAdmin menuAdmin = new MenuAdmin(aprovService, certificadoService, grupoService, inscricaoService,
                oportunidadeService, usuarioService, ppcService, admin, curso);
        MenuExtra menuExtra = new MenuExtra(grupoService, inscricaoService, oportunidadeService);

        // inicialização dos usuários bases - por não ter um login / bd
        Coordenador coordenador = usuarioService.cadastrarCoordenador(admin, "COR1234567", "Paulo",
                "coordenador", "1234567", CargoCoordenador.COORDENADOR);

        Docente docente = usuarioService.cadastrarDocente(admin, "Geraldo", "geraldo@gmail.com",
                "docente", "9876543");

        Discente discente = usuarioService.cadastrarDiscente("Maria Luísa", "malu@gmail.com",
                "discente", "20260012341", 1, curso);

        Discente discente1 = usuarioService.cadastrarDiscente("Ana", "ana@gmail.com",
                "diretor", "20234057891", 5, curso);

        Grupo grupo = grupoService.criar("Liga A", "Somos a liga A", "a@gmail.com",
                docente.getId());

        DiscenteDiretor diretor = usuarioService.promover(discente1, grupo, "VICE");

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
                    MenuDiscente menuDiscente = new MenuDiscente(aprovService, certificadoService, grupoService,
                            inscricaoService, oportunidadeService, usuarioService, ppcService, discente);
                    menuDiscente.executar();
                    break;

                case 2:
                    System.out.println("Entrando como discente diretor. . .");
                    MenuDD menuDD = new MenuDD(aprovService, certificadoService, grupoService,
                            inscricaoService, oportunidadeService, usuarioService, ppcService, diretor, menuExtra);
                    menuDD.executar();
                    break;

                case 3:
                    System.out.println("Entrando como docente. . .");
                    MenuDocente menuDocente = new MenuDocente(aprovService, certificadoService, grupoService,
                            inscricaoService, oportunidadeService, usuarioService, ppcService, docente, menuExtra);
                    menuDocente.executar();
                    break;

                case 4:
                    System.out.println("Entrando como coordenador. . .");
                    MenuCoordenador menuCoordenador = new MenuCoordenador(aprovService, certificadoService,
                            grupoService, inscricaoService, oportunidadeService, usuarioService, ppcService,
                            coordenador, menuExtra, curso);
                    menuCoordenador.executar();
                    break;

                case 5:
                    System.out.println("Entrando como administrador. . .");
                    menuAdmin.executar();
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