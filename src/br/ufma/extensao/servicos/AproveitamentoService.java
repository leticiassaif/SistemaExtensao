package br.ufma.extensao.servicos;

import br.ufma.extensao.entidades.Aproveitamento;
import br.ufma.extensao.entidades.Inscricao;
import br.ufma.extensao.entidades.Usuario;
import br.ufma.extensao.enums.Papel;
import br.ufma.extensao.enums.StatusAproveitamento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AproveitamentoService {
    private List<Aproveitamento> aproveitamentos = new ArrayList<>();
    private int proximoId = 1;

    public Aproveitamento submeter(String discenteId, String descricao, double cargaHorariaPleiteada){
        if (discenteId == null || descricao == null || cargaHorariaPleiteada <= 0)
            throw new IllegalArgumentException("Dados do aproveitamento são inválidos!");
        String id = ("APR00" + proximoId);
        proximoId++;
        Aproveitamento aproveitamento = new Aproveitamento(id, discenteId, descricao, cargaHorariaPleiteada);
        aproveitamentos.add(aproveitamento);
        return aproveitamento;
    }

    public Aproveitamento aprovar(String id, Usuario u, double cargaPleiteada){
        if (UsuarioService.hasPermissao(u, Papel.COORDENADOR)){
            for (Aproveitamento apr : aproveitamentos){
                if (apr.getId().equals(id) || apr.getStatus() == StatusAproveitamento.PENDENTE){
                    apr.setCargaHorariaAprovada(cargaPleiteada);
                    apr.setStatus(StatusAproveitamento.APROVADO);
                }
            }
        }
        throw new IllegalArgumentException("Usuário sem permissão para essa ação!");
    }

    public Aproveitamento indeferir(String id, Usuario u){
        if (UsuarioService.hasPermissao(u, Papel.COORDENADOR)){
            for (Aproveitamento apr : aproveitamentos){
                if (apr.getId().equals(id) || apr.getStatus() == StatusAproveitamento.PENDENTE){
                    apr.setStatus(StatusAproveitamento.INDEFERIDO);
                }
            }
        }
        throw new IllegalArgumentException("Usuário sem permissão para essa ação!");
    }

    public Aproveitamento reenviar(String id){
            for (Aproveitamento apr : aproveitamentos){
                if (apr.getId().equals(id) || apr.getStatus() == StatusAproveitamento.INDEFERIDO){
                    apr.setStatus(StatusAproveitamento.PENDENTE);
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

    public List <Aproveitamento> listarPorDiscente(){
        List<Aproveitamento> resultado = new ArrayList<>();
        for (Aproveitamento apr : aproveitamentos){
            if(apr.getDiscenteId().equals(apr.getId())){
                resultado.add(apr);
            }
        }
        return resultado;
    }
}
