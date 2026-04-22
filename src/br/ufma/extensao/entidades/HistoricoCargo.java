package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.Cargo;

import java.time.LocalDate;

public class HistoricoCargo {
    Discente discente;
    Grupo grupo;
    Cargo cargo;
    LocalDate dataInicio;
    LocalDate dataFim;

    //Construtor
    public HistoricoCargo(Discente discente, Grupo grupo, Cargo cargo) {
        this.discente = discente;
        this.grupo = grupo;
        this.cargo = cargo;
        dataInicio = LocalDate.now();
        dataFim = null;
    }

    //Getters Setters
    public Discente getDiscente() {
        return discente;
    }

    public void setDiscente(Discente discente) {
        this.discente = discente;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }
}
