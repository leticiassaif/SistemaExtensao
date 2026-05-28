package br.ufma.extensao.servicos;

import br.ufma.extensao.entidades.*;
import br.ufma.extensao.enums.CargoCoordenador;
import br.ufma.extensao.enums.Papel;

import java.util.ArrayList;
import java.util.List;


public class UsuarioService {
    private List<Usuario> usuarios = new ArrayList<>();


    public Discente cadastrarDiscente(String nome, String email, String senha, String matricula, int semestreAtual, Curso curso){
        Discente discente = new Discente("DIS"+matricula, nome, email, senha, matricula, semestreAtual, curso);
        usuarios.add(discente);
        return discente;
    }

    public Docente cadastrarDocente(String nome, String email, String senha, String siape, String departamento) {
        Docente docente = new Docente("DOC-" + siape, nome, email, senha, siape, departamento);
        usuarios.add(docente);
        return docente;
    }

    public Usuario cadastrarCoordenador(String nome, String email, String senha, String siape, CargoCoordenador cargo) {
        Usuario coordenador = new Coordenador("COR"+siape, nome, email, senha, siape, cargo);
        usuarios.add(coordenador);
        return coordenador;
    }

    public DiscenteDiretor promover(Discente discente, Grupo grupo, String cargo) {
        DiscenteDiretor diretor = new DiscenteDiretor(
                discente.getNome(), discente.getEmail(), discente.getSenha(),
                discente.getMatricula(), discente.getSemestreAtual(),
                discente.getCurso(), // <- parâmetro adicionado
                grupo, cargo);

        usuarios.remove(discente); // remove o antigo
        usuarios.add(diretor); // adiciona o promovido
        return diretor;
    }

    public static boolean hasPermissao(Usuario usuario, Papel papel) {
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
    public List<Usuario> listarUsuarios() {
        return usuarios;
    }
}
