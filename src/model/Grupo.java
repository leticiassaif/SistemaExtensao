package model;

public class Grupo {
    private String nome;
    private String tipo;
    private String email;
    private String descricao;
    // private Enum<Status> status // ativo ou inativo
    private Docente responsavel;

    // Métodos personalizados
    public void addMembro(Usuario user) {} // ou discente?
}
