package model;

import enums.Papel;

public class Docente extends Usuario {
    private String siape;
    private String departamento;


    public Docente(String nome, String email, String senha, Papel papel, String siape) {
        super(nome, email, senha, papel);
        this.siape = siape;
        this.departamento = "Departamento ...";
    }
}
