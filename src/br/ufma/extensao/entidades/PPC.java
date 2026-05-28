package br.ufma.extensao.entidades;

import java.time.LocalDate;

public class PPC {
    private String id;
    private String cursoId;
    private int versao;
    private double cargaHoraria;
    private LocalDate dataInicio;

    public PPC(String id, String cursoId, int versao, double cargaHorariaExtensao) {
        this.id = id;
        this.cursoId = cursoId;
        this.versao = versao;
        this.cargaHoraria = cargaHorariaExtensao;
        dataInicio = LocalDate.now();

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

    public int getVersao() {
        return versao;
    }

    public void setVersao(int versao) {
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

    @Override
    public String toString() {
        return "PPC{" +
                "id=" + id +
                ", cursoId=" + cursoId +
                ", versao=" + versao +
                ", cargaHoraria=" + cargaHoraria +
                ", dataInicio=" + dataInicio +
                '}';
    }
}
