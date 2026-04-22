package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.StatusGrupo;

public class Grupo {
    private String id;
    private String nome;
    private String descricao;
    private String email;
    private String docenteResponsavelId;
    private StatusGrupo status;

    public Grupo(String id, String nome, String descricao, String email, String docenteResponsavelId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.email = email;
        this.docenteResponsavelId = docenteResponsavelId;
        this.status = StatusGrupo.ATIVO;
    }

    // Getters
    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public String getEmail() { return email; }
    public String getDocenteResponsavelId() { return docenteResponsavelId; }
    public StatusGrupo getStatus() { return status; }

    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setEmail(String email) { this.email = email; }
    public void setDocenteResponsavelId(String docenteResponsavelId) { this.docenteResponsavelId = docenteResponsavelId; }
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