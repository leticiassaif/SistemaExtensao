package enums;

public enum Papel {
    DISCENTE("Discente"),
    DOCENTE("Docente"),
    DISCENTE_DIRETOR("Discente Diretor");

    private String descricao;

    Papel(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
