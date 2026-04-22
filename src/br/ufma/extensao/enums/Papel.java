package br.ufma.extensao.enums;

public enum Papel {
    DISCENTE("Discente"),
    DOCENTE("Docente"),
    DISCENTE_DIRETOR("Discente Diretor"),
    ADMIN("Administrador");

    private String descricao;

    Papel(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
