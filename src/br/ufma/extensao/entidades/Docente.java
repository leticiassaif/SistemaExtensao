package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.Modalidade;
import br.ufma.extensao.enums.Papel;
import br.ufma.extensao.enums.TipoOportunidade;

import java.time.LocalDate;

public class Docente extends Usuario {
    private String siape;
    private String departamento;

    // Métodos especiais
    public Docente(String nome, String email, String senha, String siape) {
        super(nome, email, senha, Papel.DOCENTE);

        if (siape == null) {
            throw new IllegalArgumentException("SIAPE obrigatório");
        }

        this.siape = siape;
        this.departamento = "Departamento de Informática";
    }

    public String getSiape() {
        return siape;
    }
    public String getDepartamento() {
        return departamento;
    }

}
