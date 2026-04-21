package model;

import java.util.ArrayList;
import java.util.List;

public class Grupo {
    private String nome;
    private String tipo;
    private String email;
    private String descricao;
    private Docente responsavel;
    private boolean ativo;
    private List<Discente> membros;

    // Construtor
    public Grupo(String nome, String tipo, String email, String descricao, Docente responsavel) {
        this.nome = nome;
        this.tipo = tipo;
        this.email = email;
        this.descricao = descricao;
        this.responsavel = responsavel;
        this.ativo = true;
        this.membros = new ArrayList<>();
    }

    //Funções de Add e Remove de discentes
    public void addMembro(Discente d) {
        if (!membros.contains(d)) {
            membros.add(d);
        }
    }

    public void removeMembro(Discente d) {
        membros.remove(d);
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

    public boolean isAtivo() {
        return ativo;
    }

    public List<Discente> getMembros() {
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

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void setMembros(List<Discente> membros) {
        this.membros = membros;
    }
}
