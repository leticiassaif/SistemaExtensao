package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.StatusGrupo;

import java.util.ArrayList;
import java.util.List;

import static br.ufma.extensao.enums.StatusGrupo.ATIVO;

public class Grupo {
    private Long id;
    private String nome;
    private String email;
    private String descricao;
    private Docente responsavel;
    private StatusGrupo status;

    // Construtor
    public Grupo(Long id, String nome, String email, String descricao, Docente responsavel) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.responsavel = responsavel;
        this.status = ATIVO;
    }

    //Getters
    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEmail() {
        return email;
    }

    public String getDescricao() {
        return descricao;
    }

    public Docente getResponsavel() {
        return responsavel;
    }

    public StatusGrupo getStatus() {
        return status;
    }

    public List<Usuario> getMembros() {
        return membros;
    }

    //Setters

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setResponsavel(Docente responsavel) {
        this.responsavel = responsavel;
    }

    public void setStatus(StatusGrupo status) {
        this.status = status;
    }

    public void setMembros(List<Usuario> membros) {
        this.membros = membros;
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", responsavel=" + responsavel.getNome() +
                ", status=" + status +
                ", membros=" + membros.size() +
                '}';
    }
}
