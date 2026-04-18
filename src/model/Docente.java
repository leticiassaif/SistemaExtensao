package model;

import enums.Papel;
import java.time.LocalDate;

public class Docente extends Usuario {
    private String siape;
    private String departamento;

    // Métodos especiais
    public Docente(String nome, String email, String senha, Papel papel, String siape) {
        super(nome, email, senha, papel);

        if (siape == null) {
            throw new IllegalArgumentException("SIAPE obrigatório");
        }

        this.siape = siape;
        this.departamento = "Departamento ...";
    }

    public String getSiape() {
        return siape;
    }
    public String getDepartamento() {
        return departamento;
    }

    // Métodos personalizados
    public Oportunidade criarOportunidade(LocalDate data) {
        Oportunidade op = new Oportunidade();
        return op;
    }

    public void registrarPlanoAtividade(Oportunidade oportunidade, LocalDate data) {}
}
