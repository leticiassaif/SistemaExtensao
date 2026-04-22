package br.ufma.extensao.entidades;

public class Curso {
    private Long id;
    private String nome;
    private String codigo;

    public Curso(Long id, String nome, String codigo) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
    }

    // Getters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getCodigo() { return codigo; }

    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", codigo='" + codigo + '\'' +
                '}';
    }
}