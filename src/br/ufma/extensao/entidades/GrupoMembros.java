package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.Cargo;
import java.time.LocalDate;

public class GrupoMembros {
    private String id;
    private String grupoId;
    private String usuarioId;
    private Cargo cargo;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;

    public GrupoMembros(String id, String grupoId, String usuarioId, Cargo cargo, LocalDate dataEntrada) {
        this.id = id;
        this.grupoId = grupoId;
        this.usuarioId = usuarioId;
        this.cargo = cargo;
        this.dataEntrada = dataEntrada;
        this.dataSaida = null;
    }

    // Getters
    public String getId() { return id; }
    public String getGrupoId() { return grupoId; }
    public String getUsuarioId() { return usuarioId; }
    public Cargo getCargo() { return cargo; }
    public LocalDate getDataEntrada() { return dataEntrada; }
    public LocalDate getDataSaida() { return dataSaida; }

    // Setters
    public void setCargo(Cargo cargo) { this.cargo = cargo; }
    public void setDataSaida(LocalDate dataSaida) { this.dataSaida = dataSaida; }

    @Override
    public String toString() {
        String saida = "ativo";
        if (dataSaida != null) {
            saida = dataSaida.toString();
        }

        return "GrupoMembros{" +
                "id=" + id +
                ", grupoId=" + grupoId +
                ", usuarioId=" + usuarioId +
                ", cargo=" + cargo +
                ", dataEntrada=" + dataEntrada +
                ", dataSaida=" + saida +
                '}';
    }
}