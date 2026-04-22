package br.ufma.extensao.servicos;

import br.ufma.extensao.enums.Papel;
import br.ufma.extensao.entidades.Discente;
import br.ufma.extensao.entidades.Docente;
import br.ufma.extensao.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    List<Usuario> usuarios = new ArrayList<>();

    Discente cadastrarDiscente(String nome, String email, String senha, String matricula, int semestreAtual){
        Discente discente = new Discente(nome, email, senha, Papel.DISCENTE, matricula, semestreAtual);
        usuarios.add(discente);
        return discente;
    }

    Docente cadastrarDocente(String nome, String email, String senha, String siape) {
        Docente docente = new Docente(nome, email, senha, Papel.DOCENTE, siape);
        usuarios.add(docente);
        return docente;
    }

    // necessário?
    Usuario cadastrarAdmin(String nome, String email, String senha) {
        Usuario admin = new Usuario(nome, email, senha, Papel.ADMIN);
        usuarios.add(admin);
        return admin;
    }

    boolean hasPermissao(Usuario usuario, Papel papel) {
        return usuario.getPapel() == papel;
    }

    Usuario buscarEmail(String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }

    // possível buscar por matrícula / siape / id futuramente

    // sugestão Collections.unmodifiableList(usuarios) pois impede mudanças da lista *
    List<Usuario> listarUsuarios() {
        return usuarios;
    }
}
