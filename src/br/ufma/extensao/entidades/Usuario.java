package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.Papel;

public abstract class Usuario {

    private final String id;
    private String nome;
    private String email;
    private String senha;
    private Papel papel;
    private boolean ativo;

    // Métodos especiais
    public Usuario(String id, String nome, String email, String senha, Papel papel) {
        if (nome == null) {
            throw new IllegalArgumentException("Nome obrigatório");
        }

        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido");
        }

        if (senha == null) {
            throw new IllegalArgumentException("Senha inválida");
        }
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.papel = papel;
        this.ativo = true;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Papel getPapel() {
        return papel;
    }
    public void setPapel(Papel papel) {
        this.papel = papel;
    }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", papel=" + papel +
                ", ativo=" + ativo +
                '}';
    }
}
