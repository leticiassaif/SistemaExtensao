package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.Cargo;
import br.ufma.extensao.enums.Modalidade;
import br.ufma.extensao.enums.Papel;
import br.ufma.extensao.enums.TipoOportunidade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DiscenteDiretor extends Discente{
    private Grupo grupo;
    private Cargo cargo;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    List<HistoricoCargo> historicoCargos;

    // Métodos especiais
    public DiscenteDiretor(String nome, String email, String senha, Papel papel, String matricula, int semestreAtual,
                           Grupo grupo, Cargo cargo) {
        super(nome, email, senha, papel, matricula, semestreAtual);
        this.grupo = grupo;
        this.cargo = cargo;
        this.dataInicio = LocalDate.now();
        this.dataFim = null;
        this.historicoCargos = new ArrayList<>();
    }

    //Getters e Setters
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
    public void setDataFim() {
        this.dataFim = LocalDate.now();
    }
}
