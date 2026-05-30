package br.ufma.extensao.entidades;

import java.time.LocalDate;

public class PPC {
    private String id;
    private String cursoId;
    private String versao;
    private double cargaHoraria;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public PPC(String id, String cursoId, String versao, double cargaHorariaExtensao) {
        this.id = id;
        this.cursoId = 12345L;
        this.versao = versao;
        this.cargaHoraria = cargaHorariaExtensao;
        dataInicio = LocalDate.now();
        dataFim = null;

    }
    // getters, setters e toString()

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCursoId() {
        return cursoId;
    }

    public void setCursoId(String cursoId) {
        this.cursoId = cursoId;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public double getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(double cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    @Override
    public String toString() {
        return "PPC{" +
                "id=" + id +
                ", cursoId=" + cursoId +
                ", versao=" + versao +
                ", cargaHoraria=" + cargaHoraria +
                ", dataInicio=" + dataInicio +
                ", dataFim= " + dataFim +
                '}';
    }
}
