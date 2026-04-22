package br.ufma.extensao.enums;

public enum Cargo {
    DIRETOR("Diretor"),
    VICE_DIRETOR("Vice-Diretor"),
    TESOUREIRO("Tesoureiro"),
    MEMBRO("Membro");

    private String descricao;

    Cargo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
