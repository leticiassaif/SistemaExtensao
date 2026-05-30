package br.ufma.extensao.menus;

// serve para oportunidades e grupo!

import br.ufma.extensao.servicos.*;

public class MenuExtra {
    protected final AproveitamentoService aproveitamentoService;
    protected final GrupoService grupoService;
    protected final OportunidadeService oportunidadeService;

    public MenuExtra(AproveitamentoService aproveitamentoService, GrupoService grupoService,
                     OportunidadeService oportunidadeService) {
        this.aproveitamentoService = aproveitamentoService;
        this.grupoService = grupoService;
        this.oportunidadeService = oportunidadeService;
    }
}
