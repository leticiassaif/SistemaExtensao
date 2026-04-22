package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.Modalidade;
import br.ufma.extensao.enums.StatusOportunidade;
import br.ufma.extensao.enums.TipoOportunidade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Oportunidade {
    private String titulo;
    private String descricao;
    private TipoOportunidade tipo;
    private Modalidade modalidade;
    private int cargaHoraria;
    private int vagas;
    private StatusOportunidade status;
    private LocalDate inicio;
    private LocalDate fim;
    private Usuario autor;
    private Docente responsavel;
    private List<Inscricao> inscricoes;

    //Metodos
    public Oportunidade(String titulo, TipoOportunidade tipo, Modalidade modalidade, int cargaHoraria, int vagas, LocalDate inicio, LocalDate fim, Usuario autor, Docente responsavel) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.modalidade = modalidade;
        this.cargaHoraria = cargaHoraria;
        this.vagas = vagas;
        this.inicio = inicio;
        this.fim = fim;
        this.autor = autor;
        this.responsavel = responsavel;

        // RF011:
        this.status = StatusOportunidade.RASCUNHO;
        this.inscricoes = new ArrayList<>();
    }

    public Oportunidade(String titulo, String descricao, TipoOportunidade tipo, Modalidade modalidade, int cargaHoraria, int vagas, StatusOportunidade pendente, LocalDate inicio, LocalDate fim, Usuario autor, Docente responsavelId) {
    }


    public void submeterParaAprovacao() {
        if (this.status != StatusOportunidade.RASCUNHO) {
            throw new IllegalStateException("A Oportunidade deve ter como Status, 'Rascunho'");
        }
        this.status = StatusOportunidade.AGUARDANDO_APROVACAO;
    }

    public void publicar() {
        if (this.status != StatusOportunidade.AGUARDANDO_APROVACAO) {
            throw new IllegalStateException("A Oportunidade deve ter como Status, 'Aguardando aprovação'");
        }
        this.status = StatusOportunidade.ABERTA;
    }

    public void fecharInscricoes() {

        if (this.status != StatusOportunidade.ABERTA) {
            throw new IllegalStateException("A Oportunidade deve ter como Status, 'Aberta'");
        }
        this.status = StatusOportunidade.EM_EXECUCAO;
    }

    public void cancelar() {

        if (this.status != StatusOportunidade.EM_EXECUCAO) {
            throw new IllegalStateException("A Oportunidade deve ter como Status, 'Em execução'");
        }
        this.status = StatusOportunidade.ENCERRADA;
    }


    //Getter/Setters
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
        this.tipo = (TipoOportunidade) tipo;
    }

    public Enum<Modalidade> getModalidade() {
        return modalidade;
    }

    public void setModalidade(Enum<Modalidade> modalidade) {
        this.modalidade = (Modalidade) modalidade;
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

    public StatusOportunidade getStatus() {
        return status;
    }

    public void setStatus(StatusOportunidade status) {
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


}
