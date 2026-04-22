package br.ufma.extensao.enums;

public enum Papel {
    DISCENTE("Discente"),
    DISCENTE_DIRETOR("discente diretor"),
    DOCENTE("Docente"),
    COORDENADOR("Coordenador"),
    ADMIN("Administrador");

    private String descricao;

    Papel(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
