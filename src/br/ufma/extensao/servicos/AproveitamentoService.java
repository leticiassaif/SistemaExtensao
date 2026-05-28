package br.ufma.extensao.servicos;

import br.ufma.extensao.entidades.Aproveitamento;
import br.ufma.extensao.entidades.Inscricao;
import br.ufma.extensao.entidades.Usuario;
import br.ufma.extensao.enums.Papel;
import br.ufma.extensao.enums.StatusAproveitamento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AproveitamentoService {
    private List<Aproveitamento> aproveitamentos = new ArrayList<>();


    public Aproveitamento submeter(String discenteId, String descricao, double cargaHorariaPleiteada){
        if (discenteId == null || descricao == null || cargaHorariaPleiteada <= 0)
            throw new IllegalArgumentException("Dados do aproveitamento são inválidos!");
        String id = "APR-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Aproveitamento aproveitamento = new Aproveitamento(id, discenteId, descricao, cargaHorariaPleiteada);
        aproveitamentos.add(aproveitamento);
        return aproveitamento;
    }

    public Aproveitamento aprovar(String id, Usuario u, double cargaPleiteada) {
        if (!UsuarioService.hasPermissao(u, Papel.COORDENADOR))
            throw new SecurityException("Usuário sem permissão para essa ação!");
        for (Aproveitamento apr : aproveitamentos) {
            if (apr.getId().equals(id)) {
                if (apr.getStatus() != StatusAproveitamento.PENDENTE)
                    throw new IllegalStateException("Aproveitamento não está PENDENTE.");
                apr.setCargaHorariaAprovada(cargaPleiteada);
                apr.setStatus(StatusAproveitamento.APROVADO);
                return apr;
            }
        }
        throw new IllegalArgumentException("Aproveitamento não encontrado: " + id);
    }

    public Aproveitamento indeferir(String id, Usuario u) {
        if (!UsuarioService.hasPermissao(u, Papel.COORDENADOR))
            throw new SecurityException("Usuário sem permissão para essa ação!");

        for (Aproveitamento apr : aproveitamentos) {
            if (apr.getId().equals(id)) {
                if (apr.getStatus() != StatusAproveitamento.PENDENTE)
                    throw new IllegalStateException("Aproveitamento não está PENDENTE.");
                apr.setStatus(StatusAproveitamento.INDEFERIDO);
                return apr;
            }
        }
        throw new IllegalArgumentException("Aproveitamento não encontrado: " + id);
    }

    public Aproveitamento reenviar(String id){
        for (Aproveitamento apr : aproveitamentos) {
            if (apr.getId().equals(id) && apr.getStatus() == StatusAproveitamento.INDEFERIDO) {
                apr.setStatus(StatusAproveitamento.PENDENTE);
                return apr;
                }
            }
        throw new IllegalArgumentException("Impossível realizar ação!");
    }



    public List <Aproveitamento> listarPendentes(){
        List <Aproveitamento> resultado = new ArrayList<>();
        for (Aproveitamento apr : aproveitamentos) {
            if (apr.getStatus() == StatusAproveitamento.PENDENTE)
                resultado.add(apr);
        }
        return resultado;
    }

    public List <Aproveitamento> listarPorDiscente(String discenteId){
        List<Aproveitamento> resultado = new ArrayList<>();
        for (Aproveitamento apr : aproveitamentos) {
            if (apr.getDiscenteId().equals(discenteId)) {
                resultado.add(apr);
            }
        }
        return resultado;
    }
}
