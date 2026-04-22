package br.ufma.extensao.enums;

public enum Papel {
    DISCENTE("Discente"),
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
