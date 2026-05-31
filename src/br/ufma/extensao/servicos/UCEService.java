package br.ufma.extensao.servicos;

import br.ufma.extensao.entidades.PPC;
import br.ufma.extensao.entidades.UCE;
import br.ufma.extensao.entidades.Usuario;
import br.ufma.extensao.enums.Papel;

import java.util.ArrayList;
import java.util.List;

public class UCEService {
    private final PPCService ppcService;
    private List<UCE> uces = new ArrayList<>();

    public UCEService(PPCService ppcService) {
        this.ppcService = ppcService;
    }

    // cadastrar uma uce em um ppc específico
    public UCE cadastrarUCE(Usuario autor, String nome, double cargaHoraria, PPC ppc) {
        if (hasPermissao(autor, Papel.ADMIN) || hasPermissao(autor, Papel.COORDENADOR)) {
            UCE uce = new UCE(nome, cargaHoraria, ppc);
            uces.add(uce);
            return uce;
        }

        return null;
    }

    // lista as uces de um ppc específico
    public List<UCE> listarUCEs(PPC ppc) {
        List<UCE> resultado = new ArrayList<>();
        for (UCE u : uces) {
            if (u.getPpc().equals(ppc)) {
                resultado.add(u);
            }
        }
        return resultado;
    }

    static boolean hasPermissao(Usuario u, Papel p) {
        return u.getPapel() == p;
    }
}
