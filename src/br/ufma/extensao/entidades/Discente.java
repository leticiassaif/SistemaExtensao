package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.Papel;

public class Discente extends Usuario {
    private String matricula;
    private int semestreAtual;
    private double totalHorasConcluidas;
    private PPC ppc;
    private Curso curso;

    public Discente(String id, String nome, String email, String senha, String matricula, int semestreAtual, PPC ppc,
                    Curso curso) {
        super(id, nome, email, senha, Papel.DISCENTE);

        if (matricula == null || matricula.isBlank())
            throw new IllegalArgumentException("Matrícula obrigatória");

        if (semestreAtual < 1)
            throw new IllegalArgumentException("Semestre inválido");

        if (ppc == null)
            throw new IllegalArgumentException("PPC obrigatório");

        this.matricula = matricula;
        this.semestreAtual = semestreAtual;
        this.totalHorasConcluidas = 0.0;
        this.ppc = ppc;
        this.curso = curso;
    }

    public String getMatricula() { return matricula; }

    public int getSemestreAtual() { return semestreAtual; }
    public void setSemestreAtual(int semestreAtual) { this.semestreAtual = semestreAtual; }

    public double getTotalHorasConcluidas() { return totalHorasConcluidas; }
    public void setTotalHorasConcluidas(double totalHorasConcluidas) { this.totalHorasConcluidas = totalHorasConcluidas; }

    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }

    public PPC getPpc() {
        return ppc;
    }
    public void setPpc(PPC ppc) {
        this.ppc = ppc;
    }

    @Override
    public String toString() {
        return "Discente{" +
                "matricula='" + matricula + '\'' +
                ", nome='" + getNome() + '\'' +
                ", semestreAtual=" + semestreAtual +
                ", totalHorasConcluidas=" + totalHorasConcluidas +
                ", curso=" + curso +
                ", PPC =" + ppc.getVersao() +
                ", ativo=" + isAtivo() +
                '}';
    }
}