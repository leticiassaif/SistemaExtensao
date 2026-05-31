package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.StatusInscricao;
import java.time.LocalDateTime;

public class Inscricao {
    private String id;
    private Discente discente;
    private Oportunidade oportunidade;
    private StatusInscricao status;
    private String motivacao;
    private String justificativaCancelamento;
    private LocalDateTime dataInscricao;

    public Inscricao(String id, Discente discente, Oportunidade oportunidade, String motivacao) {
        this.id = id;
        this.discente = discente;
        this.oportunidade = oportunidade;
        this.motivacao = motivacao;
        this.justificativaCancelamento = null;
        this.dataInscricao = LocalDateTime.now();
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

    public LocalDateTime getDataInscricao() {
        return dataInscricao;
    }

    public String getJustificativaCancelamento() {
        return justificativaCancelamento;
    }

    //Setters
    public void setStatus(StatusInscricao status) {
        this.status = status;
    }

    public void setMotivacao(String motivacao) {
        this.motivacao = motivacao;
    }

    public void setJustificativaCancelamento(String justificativaCancelamento) {
        this.justificativaCancelamento = justificativaCancelamento;
    }



    @Override
    public String toString() {

        String cancelamento = "";
        if (justificativaCancelamento != null) {
            cancelamento = ", justificativaCancelamento='" + justificativaCancelamento + "'";
        }

        return "Inscricao{" +
                "id=" + id +
                ", discente=" + discente.getNome() +
                ", oportunidade=" + oportunidade.getTitulo() +
                ", status=" + status +
                ", motivacao='" + motivacao + '\'' +
                ", dataInscricao=" + dataInscricao +
                cancelamento +  // aparece só se não for null
                '}';
    }
}