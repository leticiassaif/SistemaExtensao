package br.ufma.extensao.entidades;

public class UCE {
    private String nome;
    private double cargaHoraria;
    private PPC ppc;

    public UCE(String nome, double cargaHoraria, PPC ppc) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.ppc = ppc;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getCargaHoraria() {
        return cargaHoraria;
    }
    public void setCargaHoraria(double cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public PPC getPpc() {
        return ppc;
    }
    public void setPpc(PPC ppc) {
        this.ppc = ppc;
    }
}
