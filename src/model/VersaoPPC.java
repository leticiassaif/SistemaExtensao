package model;

import java.time.LocalDate;

public class VersaoPPC {
    private int horasExtensao;
    private String versao;
    private LocalDate dataVigencia;
    private Usuario autor;

    //Construtor
    public VersaoPPC(int horasExtensao, String versao, Usuario autor) {
        this.horasExtensao = horasExtensao;
        this.versao = versao;
        this.autor = autor;
        this.dataVigencia = LocalDate.now(); 
    }

    //Getters
    public Usuario getAutor() {
        return autor;
    }

    public LocalDate getDataVigencia() {
        return dataVigencia;
    }

    public String getVersao() {
        return versao;
    }

    public int getHorasExtensao() {
        return horasExtensao;
    }

    //Setters
    public void setHorasExtensao(int horasExtensao) {
        this.horasExtensao = horasExtensao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public void setDataVigencia(LocalDate dataVigencia) {
        this.dataVigencia = dataVigencia;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }
}
