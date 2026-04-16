package model;

import enums.Modalidade;
import enums.StatusOportunidade;
import enums.TipoOportunidade;

import java.time.LocalDate;

public class Oportunidade {
    private String titulo;
    private String descricao;
    private Enum<TipoOportunidade> tipo;
    private Enum<Modalidade> modalidade;
    private int cargaHoraria;
    private int vagas;
    private Enum<StatusOportunidade> status;
    private LocalDate inicio;
    private LocalDate fim;
    private Usuario autor;
    private Docente responsavel;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Enum<TipoOportunidade> getTipo() {
        return tipo;
    }

    public void setTipo(Enum<TipoOportunidade> tipo) {
        this.tipo = tipo;
    }

    public Enum<Modalidade> getModalidade() {
        return modalidade;
    }

    public void setModalidade(Enum<Modalidade> modalidade) {
        this.modalidade = modalidade;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public Enum<StatusOportunidade> getStatus() {
        return status;
    }

    public void setStatus(Enum<StatusOportunidade> status) {
        this.status = status;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
        this.fim = fim;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Docente getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Docente responsavel) {
        this.responsavel = responsavel;
    }

    // Metodos personalizados
    /*
    public void publicar() {
        if (this.status.getStatus() == PENDENTE) {
            this.status = Publicada;
        }
    }
    */

    public void fecharInscricoes() {}
}
