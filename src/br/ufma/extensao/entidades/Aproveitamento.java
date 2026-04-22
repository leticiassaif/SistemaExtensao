package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.StatusAproveitamento;
import java.time.LocalDate;

public class Aproveitamento {
    private Long id;
    private Long discenteId;
    private String descricaoAtividade;
    private double cargaHorariaPleiteada;
    private double cargaHorariaAprovada;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private StatusAproveitamento status;
    private String parecer;

    public Aproveitamento(Long id, Long discenteId, String descricaoAtividade,
                          double cargaHorariaPleiteada) {
        this.id = id;
        this.discenteId = discenteId;
        this.descricaoAtividade = descricaoAtividade;
        this.cargaHorariaPleiteada = cargaHorariaPleiteada;
        this.cargaHorariaAprovada = 0.0;
        this.dataInicio = LocalDate.now();
        this.dataFim = null;
        this.status = StatusAproveitamento.PENDENTE;
        this.parecer = null;
    }

    // Getters
    public Long getId() { return id; }
    public Long getDiscenteId() { return discenteId; }
    public String getDescricaoAtividade() { return descricaoAtividade; }
    public double getCargaHorariaPleiteada() { return cargaHorariaPleiteada; }
    public double getCargaHorariaAprovada() { return cargaHorariaAprovada; }
    public LocalDate getDataInicio() { return dataInicio; }
    public LocalDate getDataFim() { return dataFim; }
    public StatusAproveitamento getStatus() { return status; }
    public String getParecer() { return parecer; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setDiscenteId(Long discenteId) { this.discenteId = discenteId; }
    public void setDescricaoAtividade(String descricaoAtividade) { this.descricaoAtividade = descricaoAtividade; }
    public void setCargaHorariaPleiteada(double cargaHorariaPleiteada) { this.cargaHorariaPleiteada = cargaHorariaPleiteada; }
    public void setCargaHorariaAprovada(double cargaHorariaAprovada) { this.cargaHorariaAprovada = cargaHorariaAprovada; }
//    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
//    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
    public void setStatus(StatusAproveitamento status) { this.status = status; }
    public void setParecer(String parecer) { this.parecer = parecer; }

    @Override
    public String toString() {
        return "Aproveitamento{" +
                "id=" + id +
                ", discenteId=" + discenteId +
                ", descricao='" + descricaoAtividade + '\'' +
                ", cargaPleiteada=" + cargaHorariaPleiteada +
                ", cargaAprovada=" + cargaHorariaAprovada +
                ", periodo=" + dataInicio + " a " + dataFim +
                ", status=" + status +
                ", parecer='" + parecer + '\'' +
                '}';
    }
}