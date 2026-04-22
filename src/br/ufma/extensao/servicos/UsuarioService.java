package br.ufma.extensao.servicos;

import br.ufma.extensao.entidades.*;
import br.ufma.extensao.enums.CargoCoordenador;
import br.ufma.extensao.enums.Papel;

import java.util.ArrayList;
import java.util.List;


public class UsuarioService {
    private List<Usuario> usuarios = new ArrayList<>();
    private int proximoId = 1;

    //todo adicionar parametros p/ cadastro de todas as classes

    public Discente cadastrarDiscente(String nome, String email, String senha, String matricula, int semestreAtual, Curso curso){
        String id = ("DIS00" + proximoId);
        proximoId ++;
        Discente discente = new Discente(id, nome, email, senha, matricula, semestreAtual, curso);
        usuarios.add(discente);
        return discente;
    }

    public Docente cadastrarDocente(String nome, String email, String senha, String siape, String departamento) {
        String id = ("DOC00" + proximoId);
        Docente docente = new Docente(id, nome, email, senha, siape, departamento);
        usuarios.add(docente);
        return docente;
    }

    public Usuario cadastrarCoordenador(String nome, String email, String senha, String siape, CargoCoordenador cargo) {
        String id = ("COR00" + proximoId);
        Usuario coordenador = new Coordenador(id, nome, email, senha, siape, cargo);
        usuarios.add(coordenador);
        return coordenador;
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
    public List<Usuario> listarUsuarios() {
        return usuarios;
    }
}
