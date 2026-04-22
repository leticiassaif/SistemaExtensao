package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.StatusAssinatura;
import java.awt.*;
import java.time.LocalDate;

public class Certificado {
    private String uuidHash;
    private Discente discente;
    private Oportunidade oportunidade;
    private LocalDate dataEmissao;
    private int horas;
    private String certificadoPath;
    private Enum<StatusAssinatura> statusAssinatura;

    // Métodos especiais

    // Métodos personalizados
    public Image gerarQRCode() {}

    public boolean verificarAutenticidade(String hash) {}
}
