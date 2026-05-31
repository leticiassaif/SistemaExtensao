package br.ufma.extensao.menus;
import br.ufma.extensao.servicos.*;
import java.util.Scanner;

public abstract class Menu {
    protected final AproveitamentoService aproveitamentoService;
    protected final CertificadoService certificadoService;
    protected final GrupoService grupoService;
    protected final InscricaoService inscricaoService;
    protected final OportunidadeService oportunidadeService;
    protected final UsuarioService usuarioService;
    protected final PPCService ppcService;
    protected final Scanner scanner;

    public Menu(AproveitamentoService aproveitamentoService, CertificadoService certificadoService,
                GrupoService grupoService, InscricaoService inscricaoService, OportunidadeService oportunidadeService,
                UsuarioService usuarioService, PPCService ppcService, Scanner scanner) {
        this.aproveitamentoService = aproveitamentoService;
        this.certificadoService = certificadoService;
        this.grupoService = grupoService;
        this.inscricaoService = inscricaoService;
        this.oportunidadeService = oportunidadeService;
        this.usuarioService = usuarioService;
        this.ppcService = ppcService;
        this.scanner = scanner;
    }

    public abstract void imprimir();

    public abstract void executar();

    protected static void limparBuffer(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}
