package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.Papel;

public class Discente extends Usuario {
    private String matricula;
    private int semestreAtual;
    private Curso curso;
    private int codigoCurso;
    private String versaoPPC;

    // Métodos especiais
    public Discente(String nome, String email, String senha, Papel papel, String matricula, int semestreAtual) {
        super(nome, email, senha, papel);

        if (matricula == null) {
            throw new IllegalArgumentException("Matrícula obrigatória");
        }

        if (semestreAtual < 1) {
            throw new IllegalArgumentException("Semestre inválido");
        }

        this.matricula = matricula;
        this.semestreAtual = semestreAtual;
        this.curso = new Curso(codigoCurso, versaoPPC); 
    }

    public String getMatricula() {
        return matricula;
    }

    public int getSemestreAtual() {
        return semestreAtual;
    }
    public void setSemestreAtual() {
        this.semestreAtual += 1;
    }

    public Curso getCurso() {
        return curso;
    }
}
