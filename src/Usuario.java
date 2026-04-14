public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    // papel ?
    private boolean ativo;
    /* email verificado
    codigo
    coodigo expira em
    created at
    updated at
    deleted at
    */

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    /* public void setNome(String nome) {
        this.nome = nome;
    } */

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

    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
