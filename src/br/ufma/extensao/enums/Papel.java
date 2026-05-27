package br.ufma.extensao.enums;

public enum Papel {
    DISCENTE("Discente"),
    DISCENTE_DIRETOR("Discente Diretor"),
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
