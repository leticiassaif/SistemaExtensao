package model;

import enums.StatusAproveitamento;

import java.io.File;

public class Aproveitamento {
    private Discente discente;
    private String descricao;
    private String instituicao;
    private int horas;
    private Enum<StatusAproveitamento> status;
    private String certificadoPath;
    private Usuario avaliador;
    private String motivoRejeicao;

    // Métodos especiais

    // Métodos personalizados
    public boolean uploadCertificado(File file) {}
}
