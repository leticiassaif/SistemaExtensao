package model;

import enums.Papel;
import java.time.LocalDate;

public class Docente extends Usuario {
    private String siape;
    private String departamento;

    public Docente(String nome, String email, String senha, Papel papel, String siape) {
        super(nome, email, senha, papel);
        this.siape = siape;
        this.departamento = "Departamento ...";
    }

    // Métodos especiais
    public String getSiape() {
        return siape;
    }

    public String getDepartamento() {
        return departamento;
    }

    // Métodos personalizados
    public Oportunidade criarOportunidade(LocalDate data) {}

    public void registrarPlanoAtividade(Oportunidade oportunidade, LocalDate data) {}
}
