package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.StatusInscricao;
import java.time.LocalDate;

public class Inscricao {
    private String id;
    private Discente discente;
    private Oportunidade oportunidade;
    private StatusInscricao status;
    private String motivacao;
    private LocalDate dataInscricao;

    public Inscricao(String id, Discente discente, Oportunidade oportunidade, String motivacao) {
        this.id = id;
        this.discente = discente;
        this.oportunidade = oportunidade;
        this.motivacao = motivacao;
        this.dataInscricao = LocalDate.now();
        this.status = StatusInscricao.PENDENTE;
    }

    // Getters
    public String getId() {
        return id;
    }

    public Discente getDiscente() {
        return discente;
    }

    public Oportunidade getOportunidade() {
        return oportunidade;
    }

    public StatusInscricao getStatus() {
        return status;
    }

    public String getMotivacao() {
        return motivacao;
    }

    public LocalDate getDataInscricao() {
        return dataInscricao;
    }

    //Setters
    public void setStatus(StatusInscricao status) {
        this.status = status;
    }

    public void setMotivacao(String motivacao) {
        this.motivacao = motivacao;
    }

    @Override
    public String toString() {
        return "Inscricao{" +
                "id=" + id +
                ", discente=" + discente.getNome() +
                ", oportunidade=" + oportunidade.getTitulo() +
                ", status=" + status +
                ", motivacao='" + motivacao + '\'' +
                ", dataInscricao=" + dataInscricao +
                '}';
    }
}