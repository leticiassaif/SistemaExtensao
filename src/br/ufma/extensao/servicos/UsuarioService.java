package br.ufma.extensao.servicos;

import br.ufma.extensao.entidades.*;
import br.ufma.extensao.enums.CargoCoordenador;
import br.ufma.extensao.enums.Papel;

import java.util.ArrayList;
import java.util.List;


public class UsuarioService {
    private List<Usuario> usuarios = new ArrayList<>();

    private PPCService ppcService;

    public UsuarioService(PPCService ppcService) {
        this.ppcService = ppcService;
    }

    public Discente cadastrarDiscente(String nome, String email, String senha, String matricula, int semestreAtual, Curso curso){

        Discente discente = new Discente("DIS"+matricula, nome, email, senha, matricula, semestreAtual, ppcService.buscarVigente(curso),curso);

        usuarios.add(discente);
        return discente;
    }

    public Docente cadastrarDocente(Usuario usuario, String nome, String email, String senha, String siape) {

        if (hasPermissao(usuario, Papel.ADMIN)) {
            Docente docente = new Docente("DOC" + siape, nome, email, senha, siape);
            usuarios.add(docente);
            return docente;
        }

        throw new IllegalStateException("Você deve ser administrador para cadastrar um Docente");
    }

    public Usuario cadastrarCoordenador(Usuario usuario, String nome, String email, String senha, String siape, CargoCoordenador cargo) {

        if (hasPermissao(usuario, Papel.ADMIN)) {
            Usuario coordenador = new Coordenador("COR"+siape, nome, email, senha, siape, cargo);
            usuarios.add(coordenador);
            return coordenador;
        }
        throw new IllegalStateException("Você deve ser administrador para cadastrar um Coordenador");
    }

    public DiscenteDiretor promover(Discente discente, Grupo grupo, String cargo) {
        DiscenteDiretor diretor = new DiscenteDiretor(
                discente.getNome(), discente.getEmail(), discente.getSenha(),
                discente.getMatricula(), discente.getSemestreAtual(),
                discente.getCurso(),
                discente.getPpc(), grupo, cargo);

        usuarios.remove(discente); // remove o antigo
        usuarios.add(diretor); // adiciona o promovido
        return diretor;
    }

    public void desativar(Usuario solicitante, Usuario alvo) {

        if (!podeGerenciarUsuario(solicitante, alvo)) {
            throw new IllegalStateException("O solicitante não possui permissão para desativar o usuário");
        }
            alvo.setAtivo(false);
    }

    public void anonimizar(Usuario solicitante, Usuario alvo) {
        if (!podeGerenciarUsuario(solicitante, alvo)) {
            throw new IllegalStateException("O solicitante não possui permissão para desativar o usuário");
        }

        alvo.setAtivo(false);
        alvo.setEmail("anonimo_" + alvo.getId() + "@sistema.local");
        alvo.setSenha("");
        alvo.setNome("Usuário Anonimizado");

    }

    public static boolean podeGerenciarUsuario(Usuario solicitante, Usuario alvo) {
        if (solicitante == null || alvo == null) {
            throw new IllegalArgumentException("Solicitante ou o Alvo não existem");
        }

        boolean coordenadorDesativandoDiscente  = hasPermissao(solicitante, Papel.COORDENADOR) && (alvo.getPapel() == Papel.DISCENTE || alvo.getPapel() == Papel.DISCENTE_DIRETOR);
        boolean isAdmin = hasPermissao(solicitante, Papel.ADMIN);
        boolean isSameUser = solicitante.getId().equals(alvo.getId());

        return isAdmin || isSameUser || coordenadorDesativandoDiscente;
    }

    public static boolean hasPermissao(Usuario usuario, Papel papel) {
        return usuario.getPapel() == papel;
    }

    public Usuario buscarEmail(String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }

    public Usuario buscarPorId(String usuarioId) {
        for (Usuario u : usuarios) {
            if (u.getId().equals(usuarioId)) {
                return u;
            }
        }
        return null;
    }

    // sugestão Collections.unmodifiableList(usuarios) pois impede mudanças da lista *
    public List<Usuario> listarUsuarios() {
        return usuarios;
    }
}
