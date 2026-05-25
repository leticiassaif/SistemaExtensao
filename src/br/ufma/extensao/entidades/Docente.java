package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.Papel;

public class Docente extends Usuario {
    private String siape;
    private String departamento;

    public Docente(String id, String nome, String email, String senha, String siape, String departamento) {
        super(id, nome, email, senha, Papel.DOCENTE);
        this.siape = siape;
        this.departamento = departamento;
    }

    // Getters
    public String getSiape() { return siape; }
    public String getDepartamento() { return departamento; }

    // Setters
    public void setSiape(String siape) { this.siape = siape; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

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