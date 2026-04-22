package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.Modalidade;
import br.ufma.extensao.enums.StatusOportunidade;
import br.ufma.extensao.enums.TipoOportunidade;

import java.time.LocalDate;

public class Oportunidade {
    private Long id;
    private String titulo;
    private String descricao;
    private TipoOportunidade tipo;
    private Modalidade modalidade;
    private double cargaHoraria;
    private int vagas;
    private StatusOportunidade status;
    private LocalDate inicio;
    private LocalDate fim;
    private Long docenteResponsavelId;
    private Usuario autor;

    public Oportunidade(Long id, String titulo, String descricao, TipoOportunidade tipo, Modalidade modalidade, int cargaHoraria, int vagas, Long docenteResponsavelId, LocalDate inicio, LocalDate fim, Usuario autor) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipo = tipo;
        this.modalidade = modalidade;
        this.autor = autor;
        this.cargaHoraria = cargaHoraria;
        this.vagas = vagas;
        this.docenteResponsavelId = docenteResponsavelId;
        this.inicio = inicio;
        this.fim = fim;
        this.status = StatusOportunidade.RASCUNHO;
    }


    // Getters
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public TipoOportunidade getTipo() { return tipo; }
    public Modalidade getModalidade() { return modalidade; }
    public double getCargaHoraria() { return cargaHoraria; }
    public int getVagas() { return vagas; }
    public Usuario getAutor() { return autor; }
    public StatusOportunidade getStatus() { return status; }
    public LocalDate getInicio() { return inicio; }
    public LocalDate getFim() { return fim; }
    public Long getDocenteResponsavelId() { return docenteResponsavelId; }

    // Setters
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setTipo(TipoOportunidade tipo) { this.tipo = tipo; }
    public void setModalidade(Modalidade modalidade) { this.modalidade = modalidade; }
    public void setCargaHoraria(double cargaHoraria) { this.cargaHoraria = cargaHoraria; }
    public void setVagas(int vagas) { this.vagas = vagas; }
    public void setStatus(StatusOportunidade status) { this.status = status; }
    public void setInicio(LocalDate inicio) { this.inicio = inicio; }
    public void setFim(LocalDate fim) { this.fim = fim; }
    public void setDocenteResponsavelId(Long docenteResponsavelId) { this.docenteResponsavelId = docenteResponsavelId; }
    public void setAutor(Usuario autor) { this.autor = autor; }

    @Override
    public String toString() {
        return "Oportunidade{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", tipo=" + tipo +
                ", modalidade=" + modalidade +
                ", cargaHoraria=" + cargaHoraria +
                ", vagas=" + vagas +
                ", status=" + status +
                ", inicio=" + inicio +
                ", fim=" + fim +
                ", docenteResponsavelId=" + docenteResponsavelId +
                '}';
    }
}