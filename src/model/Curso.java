package model;

import java.util.List;

public class Curso {
    private String nome;
    private int codigo;
    private int cargaHoraria;
    private String versaoPPC;

    // Métodos especiais
    // o curso sempre vai ser de ciência da computação
    public Curso() {
        this.nome = "Ciência da Computação";
        // this.codigo = codigo;
        this.cargaHoraria = 3450; // confirmar se está correto
        // this.versaoPPC = versaoPPC;
    }

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

    public String getVersaoPPC() {
        return versaoPPC;
    }
    public void setVersaoPPC(String versaoPPC) {
        this.versaoPPC = versaoPPC;
    }

    // Métodos personalizados
    public void atualizarPPC(int horas, String versao) {}

    public List<Discente> listarAlunosStatus (Enum<> status) {}
}
