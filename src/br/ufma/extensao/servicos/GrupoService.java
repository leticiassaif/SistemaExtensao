package br.ufma.extensao.servicos;

        import br.ufma.extensao.entidades.Grupo;
        import br.ufma.extensao.entidades.GrupoMembros;
        import br.ufma.extensao.entidades.Usuario;

        import java.util.ArrayList;
        import java.util.List;

public class GrupoService {

    List<Grupo> grupos = new ArrayList<>();
    private int proximoId = 1;

    public Grupo criar(String nome, String descricao, String email, String docenteResponsavelId) {
        if (nome == null || descricao == null || email == null || docenteResponsavelId == null)
            throw new IllegalArgumentException("Dados do grupo são inválidos!");

        String id = "GRP00" + proximoId;
        proximoId++;

        Grupo grupo = new Grupo(id, nome, descricao, email, docenteResponsavelId);
        grupos.add(grupo);
        return grupo;
    }

    public GrupoMembros adicionarMembro(String grupoId, String usuarioId) {
        for (Grupo g : grupos) {
            if (g.getId().equals(grupoId)) {
                GrupoMembros membro = new GrupoMembros(grupoId, usuarioId);
                g.getMembros().add(membro);
                return membro;
            }
        }
        throw new IllegalArgumentException("Grupo não encontrado!");
    }

    public GrupoMembros removerMembro(String grupoId, String membroId) {
        for (Grupo g : grupos) {
            if (g.getId().equals(grupoId)) {
                for (GrupoMembros m : g.getMembros()) {
                    if (m.getMembroId().equals(membroId)) {
                        g.getMembros().remove(m);
                        return m;
                    }
                }
                throw new IllegalArgumentException("Membro não encontrado no grupo!");
            }
        }
        throw new IllegalArgumentException("Grupo não encontrado!");
    }

    public List<GrupoMembros> listarMembrosPorGrupo(String grupoId) {
        for (Grupo g : grupos) {
            if (g.getId().equals(grupoId)) {
                return g.getMembros();
            }
        }
        throw new IllegalArgumentException("Grupo não encontrado!");
    }
}
