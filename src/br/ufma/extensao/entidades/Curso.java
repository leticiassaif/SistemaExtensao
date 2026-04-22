package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.StatusInscricao;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    private String nome;
    private String codigo;
    private int cargaHoraria;
    private String versaoPPC; //Versão atual


    // Métodos especiais
    // o curso sempre vai ser de ciência da computação
    public Curso(String nome, String codigoCurso, String versaoPPC, int cargaHoraria) {
        this.nome = nome;
        this.codigo = codigoCurso; //confirmar?
        this.versaoPPC = versaoPPC;
        this.cargaHoraria = cargaHoraria; // confirmar se está correto
    }

    //Getters e Setters
    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setVersaoPPC(String versaoPPC) {
        this.versaoPPC = versaoPPC;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "nome='" + nome + '\'' +
                ", codigo='" + codigo + '\'' +
                ", cargaHoraria=" + cargaHoraria +
                ", versaoPPC='" + versaoPPC + '\'' +
                '}';
    }

}
