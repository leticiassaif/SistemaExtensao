package br.ufma.extensao.servicos;

import br.ufma.extensao.entidades.Grupo;
import br.ufma.extensao.entidades.GrupoMembros;
import br.ufma.extensao.enums.Cargo;
import br.ufma.extensao.enums.StatusGrupo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GrupoService {

    private List<Grupo> grupos = new ArrayList<>();
    private List<GrupoMembros> membros = new ArrayList<>();
    private int proximoIdGrupo = 1;
    private int proximoIdMembro = 1;

    public Grupo criar(String nome, String descricao, String email, String docenteResponsavelId) {
        if (nome == null || descricao == null || email == null || docenteResponsavelId == null)
            throw new IllegalArgumentException("Dados do grupo são inválidos!");

        String id = "GRP00" + proximoIdGrupo;
        proximoIdGrupo++;

        Grupo grupo = new Grupo(id, nome, descricao, email, docenteResponsavelId);
        grupos.add(grupo);
        return grupo;
    }

    public GrupoMembros adicionarMembro(String grupoId, String usuarioId, Cargo cargo) {
        if (grupoId == null || usuarioId == null || cargo == null)
            throw new IllegalArgumentException("Dados do membro são inválidos!");

        for (Grupo g : grupos) {
            if (g.getId().equals(grupoId) && g.getStatus() == StatusGrupo.ATIVO) {
                String id = "MBR00" + proximoIdMembro;
                proximoIdMembro++;
                GrupoMembros membro = new GrupoMembros(id, grupoId, usuarioId, cargo, LocalDate.now());
                membros.add(membro);
                return membro;
            }
        }
        throw new IllegalArgumentException("Grupo não encontrado ou inativo!");
    }

    public GrupoMembros removerMembro(String membroId) {
        for (GrupoMembros m : membros) {
            if (m.getId().equals(membroId) && m.getDataSaida() == null) {
                m.setDataSaida(LocalDate.now());
                return m;
            }
        }
        throw new IllegalArgumentException("Membro não encontrado ou já removido!");
    }

    public List<GrupoMembros> listarMembrosPorGrupo(String grupoId) {
        List<GrupoMembros> resultado = new ArrayList<>();
        for (GrupoMembros m : membros) {
            if (m.getGrupoId().equals(grupoId) && m.getDataSaida() == null) {
                resultado.add(m);
            }
        }
        return resultado;
    }
}