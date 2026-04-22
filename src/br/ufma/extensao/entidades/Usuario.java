package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.Papel;
import java.util.List;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private Papel papel;
    private boolean ativo;

    // Métodos especiais
    public Usuario(String nome, String email, String senha, Papel papel) {
        if (nome == null) {
            throw new IllegalArgumentException("Nome obrigatório");
        }

        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido");
        }

        if (senha == null) {
            throw new IllegalArgumentException("Senha inválida");
        }
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.papel = papel;
        this.ativo = true;
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

    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // Métodos personalizados
    public void mudarSenha(String novaSenha) {
        if (novaSenha == null) {
            throw new IllegalArgumentException("Senha inválida");
        }
        this.senha = novaSenha;
    }

    // talvez seja melhor em discente?
    public List<Oportunidade> obterOportunidades() {}

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", papel=" + papel +
                ", ativo=" + ativo +
                '}';
    }
}
