package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.CargoCoordenador;
import br.ufma.extensao.enums.Papel;

public class Coordenador extends Usuario {
    private String siape;
    private CargoCoordenador cargo;

    public Coordenador(Long id, String nome, String email, String senha, String siape, CargoCoordenador cargo) {
        super(id, nome, email, senha, Papel.COORDENADOR);
        this.siape = siape;
        this.cargo = cargo;
    }

    // GETTERS
    public String getSiape() {
        return siape;
    }

    public CargoCoordenador getCargo() {
        return cargo;
    }

    // SETTERS
    public void setSiape(String siape) {
        this.siape = siape;
    }

    public void setCargo(CargoCoordenador cargo) {
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