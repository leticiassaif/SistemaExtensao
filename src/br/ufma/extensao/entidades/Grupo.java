package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.StatusGrupo;

public class Grupo {
    private Long id;
    private String nome;
    private String descricao;
    private String email;
    private Long docenteResponsavelId;
    private StatusGrupo status;

    public Grupo(Long id, String nome, String descricao, String email, Long docenteResponsavelId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.email = email;
        this.docenteResponsavelId = docenteResponsavelId;
        this.status = StatusGrupo.ATIVO;
    }

    // Getters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public String getEmail() { return email; }
    public Long getDocenteResponsavelId() { return docenteResponsavelId; }
    public StatusGrupo getStatus() { return status; }

    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setEmail(String email) { this.email = email; }
    public void setDocenteResponsavelId(Long docenteResponsavelId) { this.docenteResponsavelId = docenteResponsavelId; }
    public void setStatus(StatusGrupo status) { this.status = status; }

    @Override
    public String toString() {
        return "Grupo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", email='" + email + '\'' +
                ", docenteResponsavelId=" + docenteResponsavelId +
                ", status=" + status +
                '}';
    }
}