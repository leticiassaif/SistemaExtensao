package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.Papel;

/**
 * Representa um Discente que também exerce o papel de Diretor em um grupo estudantil.
 * Estende Discente e adiciona referência ao grupo e ao cargo exercido.
 */
public class DiscenteDiretor extends Discente {

    private Grupo grupo;
    private String cargo;

    public DiscenteDiretor(String nome, String email, String senha,
                           String matricula, int semestreAtual,
                           Grupo grupo, String cargo) {
        super("DD-" + matricula, nome, email, senha, matricula, semestreAtual);
                           Grupo grupo, String cargo) {
        this.setPapel(Papel.DISCENTE_DIRETOR);
        this.grupo = grupo;
        this.cargo = cargo;

    }

    public Grupo getGrupo() { return grupo; }
    public String getCargo() { return cargo; }

    public void setGrupo(Grupo grupo) { this.grupo = grupo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    @Override
    public String toString() {
        return "DiscenteDiretor{" +
                "nome='" + getNome() + '\'' +
                ", matricula='" + getMatricula() + '\'' +
                ", cargo='" + cargo + '\'' +
                ", papel=" + getPapel().getDescricao() +
                ", grupo=" + nomeDoGrupo() +
                '}';
    }

    private String nomeDoGrupo() {
        if (grupo == null) {
            return "nenhum";
        }
        return grupo.getNome();
    }
}
