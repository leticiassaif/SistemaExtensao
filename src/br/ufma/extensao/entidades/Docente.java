package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.Papel;

public class Docente extends Usuario {
    private String siape;
    private final String departamento;

    public Docente(String id, String nome, String email, String senha, String siape) {
        super(id, nome, email, senha, Papel.DOCENTE);
        this.siape = siape;
        this.departamento = "Departamento de Informática";
    }

    // Getters
    public String getSiape() { return siape; }
    public String getDepartamento() { return departamento; }

    // Setters
    public void setSiape(String siape) { this.siape = siape; }

    @Override
    public String toString() {
        return "Docente{" +
                "nome='" + getNome() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", siape='" + siape + '\'' +
                ", departamento='" + departamento + '\'' +
                ", ativo=" + isAtivo() +
                '}';
    }
}