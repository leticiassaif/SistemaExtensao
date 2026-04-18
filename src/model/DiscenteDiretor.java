package model;

import enums.Papel;
import java.time.LocalDate;

public class DiscenteDiretor extends Discente{
    private Grupo grupo;
    private String cargo;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    // Métodos especiais
    public DiscenteDiretor(String nome, String email, String senha, Papel papel, String matricula, int semestreAtual,
                           Grupo grupo, String cargo) {
        super(nome, email, senha, papel, matricula, semestreAtual);
        this.grupo = grupo;
        this.cargo = cargo;
        this.dataInicio = LocalDate.now();
        this.dataFim = null;
    }

    public Grupo getGrupo() {
        return grupo;
    }
    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }
    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}
