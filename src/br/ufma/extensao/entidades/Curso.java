package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.StatusInscricao;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    private String nome;
    private int codigo;
    private int cargaHoraria;
    private String versaoPPC; //Versão atual


    // Métodos especiais
    // o curso sempre vai ser de ciência da computação
    public Curso(int codigoCurso, String versaoPPC) {
        this.nome = "Ciência da Computação";
        this.codigo = codigoCurso; //confirmar?
        this.versaoPPC = versaoPPC;
        this.cargaHoraria = 3540; // confirmar se está correto
    }

    //Getters e Setters
    public String getNome() {
        return nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

}
