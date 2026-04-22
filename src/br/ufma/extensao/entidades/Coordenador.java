package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.Papel;

public class Coordenador extends Usuario {
    private String siape;
    private String cargo;

    public Coordenador(String nome, String email, String senha,
                       boolean ativo, String siape, String cargo) {
        super(nome, email, senha, Papel.COORDENADOR);
        this.siape = siape;
        this.cargo = cargo;
    }

    // GETTERS
    public String getSiape() {
        return siape;
    }

    public String getCargo() {
        return cargo;
    }

    // SETTERS
    public void setSiape(String siape) {
        this.siape = siape;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    // TOSTRING
    @Override
    public String toString() {
        return "Coordenador{" +
                "siape='" + siape + '\'' +
                ", cargo='" + cargo + '\'' +
                ", nome='" + getNome() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}