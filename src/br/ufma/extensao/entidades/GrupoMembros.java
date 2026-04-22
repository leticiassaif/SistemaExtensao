package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.Cargo;
import java.time.LocalDate;

public class GrupoMembros {
    private Long id;
    private Long grupoId;
    private Long usuarioId;
    private Cargo cargo;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;

    public GrupoMembros(Long id, Long grupoId, Long usuarioId, Cargo cargo, LocalDate dataEntrada) {
        this.id = id;
        this.grupoId = grupoId;
        this.usuarioId = usuarioId;
        this.cargo = cargo;
        this.dataEntrada = dataEntrada;
        this.dataSaida = null;
    }

    // Getters
    public Long getId() { return id; }
    public Long getGrupoId() { return grupoId; }
    public Long getUsuarioId() { return usuarioId; }
    public Cargo getCargo() { return cargo; }
    public LocalDate getDataEntrada() { return dataEntrada; }
    public LocalDate getDataSaida() { return dataSaida; }

    // Setters — só o que faz sentido mudar depois de criado
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