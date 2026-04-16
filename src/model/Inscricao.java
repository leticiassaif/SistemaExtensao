package model;

import enums.StatusInscricao;
import java.time.LocalDate;

public class Inscricao {
    private Oportunidade oportunidade;
    private Discente discente;
    private Enum<StatusInscricao> status;
    private String motivacao;

    // Métodos especiais

    // Métodos personalizados
    public void aprovar(LocalDate data) {}

    public void rejeitar() {}
}
