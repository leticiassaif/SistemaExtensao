package model;

import enums.Papel;

public class Discente extends Usuario {
    private String matricula;
    private int semestreAtual;
    private Curso curso;

    // Métodos especiais
    public Discente(String nome, String email, String senha, Papel papel, String matricula, int semestreAtual) {
        super(nome, email, senha, papel); // verificar
        this.matricula = matricula;
        this.semestreAtual = semestreAtual;
        // this.curso =
    }

    public String getMatricula() {
        return matricula;
    }

    public int getSemestreAtual() {
        return semestreAtual;
    }
    // verificar
    private void setSemestreAtual(int semestreAtual) {
        this.semestreAtual = semestreAtual;
    }

    public Curso getCurso() {
        return curso;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    // Métodos personalizados
    // necessário??????
    public void mudarCurso(Curso novoCurso) {

    }
}
