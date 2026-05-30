package br.ufma.extensao.entidades;

import java.time.LocalDate;

public class PPC {
    private Long id;
    private Long cursoId;
    private int versao;
    private double cargaHoraria;
    private LocalDate dataInicio;

    public PPC(Long id, int versao, double cargaHorariaExtensao) {
        this.id = id;
        this.cursoId = 12345L;
        this.versao = versao;
        this.cargaHoraria = cargaHorariaExtensao;
        dataInicio = LocalDate.now();

    }
    // getters, setters e toString()

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
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
