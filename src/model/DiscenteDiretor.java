package model;

import enums.Cargo;
import enums.Modalidade;
import enums.Papel;
import enums.TipoOportunidade;

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

    public void atribuirCargo(Discente discente, Cargo novoCargo) {
        for (HistoricoCargo h: historicoCargos) {

            //Se é aluno e ainda esta ativo...
            if (h.getDiscente().equals(discente) && h.isAtivo()) {
                h.encerrar(); // define a data de fim como hoje relativo
            }
        }
        grupo.addMembro(discente);
        HistoricoCargo novo = new HistoricoCargo(discente, grupo, novoCargo);
        historicoCargos.add(novo);

    }

    public void removerCargo(Discente discente) {
        for (HistoricoCargo h: historicoCargos) {
            //Se é aluno e ainda esta ativo...
            if (h.getDiscente().equals(discente) && h.isAtivo()) {
                h.encerrar(); // define a data de fim como hoje relativo
            }
        }
    }

    public Oportunidade criarOportunidade(String titulo, TipoOportunidade tipo, Modalidade modalidade, int cargaHoraria, int vagas, LocalDate inicio, LocalDate fim, Docente responsavel) {
        return new Oportunidade(titulo, tipo, modalidade, cargaHoraria, vagas, inicio, fim, this, responsavel);
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
