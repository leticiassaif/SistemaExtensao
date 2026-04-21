package model;

import enums.StatusInscricao;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    private String nome;
    private int codigo;
    private int cargaHoraria;
    private String versaoPPC; //Versão atual
    private List<VersaoPPC> historicoPPC;

    // Métodos especiais
    // o curso sempre vai ser de ciência da computação
    public Curso(int codigoCurso, String versaoPPC) {
        this.nome = "Ciência da Computação";
        this.codigo = codigoCurso; //confirmar?
        this.versaoPPC = versaoPPC;
        this.cargaHoraria = 3540; // confirmar se está correto
        this.historicoPPC = new ArrayList<>();
    }

    // Métodos personalizados
    public void atualizarPPC(int horas, String versao, Usuario autor) {
        this.cargaHoraria = horas;
        this.versaoPPC = versao;

        VersaoPPC novoRegistro = new VersaoPPC(horas, versao, autor);
        this.historicoPPC.add(novoRegistro);
    }

    public List<Discente> listarAlunosStatus(StatusInscricao status) {
        return new ArrayList<>();
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

    public List<VersaoPPC> getHistoricoPPC() {
        return historicoPPC;
    }

}
