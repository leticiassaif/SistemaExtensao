package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.StatusCertificado;
import java.time.LocalDate;

public class Certificado {
    private String id;
    private Discente discente;
    private Oportunidade oportunidade;
    private double cargaHorariaCumprida;
    private LocalDate dataEmissao;
    private String codigoAutenticidade;
    private StatusCertificado status;

    public Certificado(String id, Discente discente, Oportunidade oportunidade,
                       double cargaHorariaCumprida, LocalDate dataEmissao) {
        this.id = id;
        this.discente = discente;
        this.oportunidade = oportunidade;
        this.cargaHorariaCumprida = cargaHorariaCumprida;
        this.dataEmissao = dataEmissao;
        this.codigoAutenticidade = "CERT-" + id;
        this.status = StatusCertificado.PENDENTE;
    }

    // Getters
    public String getId() { return id; }
    public Discente getDiscente() { return discente; }
    public Oportunidade getOportunidade() { return oportunidade; }
    public double getCargaHorariaCumprida() { return cargaHorariaCumprida; }
    public LocalDate getDataEmissao() { return dataEmissao; }
    public String getCodigoAutenticidade() { return codigoAutenticidade; }
    public StatusCertificado getStatus() { return status; }

    // Setters
    public void setStatus(StatusCertificado status) { this.status = status; }
    public void setCargaHorariaCumprida(double cargaHorariaCumprida) { this.cargaHorariaCumprida = cargaHorariaCumprida; }

    @Override
    public String toString() {
        return "Certificado{" +
                "id=" + id +
                ", codigoAutenticidade='" + codigoAutenticidade + '\'' +
                ", discente=" + discente.getNome() +
                ", oportunidade=" + oportunidade.getTitulo() +
                ", cargaHorariaCumprida=" + cargaHorariaCumprida +
                ", dataEmissao=" + dataEmissao +
                ", status=" + status +
                '}';
    }
}