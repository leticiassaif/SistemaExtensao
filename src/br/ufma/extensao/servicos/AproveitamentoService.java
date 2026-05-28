package br.ufma.extensao.servicos;

import br.ufma.extensao.entidades.Aproveitamento;
import br.ufma.extensao.entidades.Inscricao;
import br.ufma.extensao.entidades.Usuario;
import br.ufma.extensao.enums.Papel;
import br.ufma.extensao.enums.StatusAproveitamento;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class AproveitamentoService {

    private Map<String, Aproveitamento> mapaAproveitamentos = new HashMap<>();
    private ArrayList<Aproveitamento> aproveitamentos = new ArrayList<>();


    public Aproveitamento submeter(String discenteId, String descricao, double cargaHorariaPleiteada){
        if (discenteId == null || descricao == null || cargaHorariaPleiteada <= 0)
            throw new IllegalArgumentException("Dados do aproveitamento são inválidos!");
        String id = "APR-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Aproveitamento aproveitamento = new Aproveitamento(id, discenteId, descricao, cargaHorariaPleiteada);
        mapaAproveitamentos.put(aproveitamento.getId(), aproveitamento);
        aproveitamentos.add(aproveitamento);
        return aproveitamento;
    }

    public Aproveitamento aprovar(String id, Usuario u, double cargaPleiteada){
        if (UsuarioService.hasPermissao(u, Papel.COORDENADOR)){
            Aproveitamento apr = mapaAproveitamentos.get(id);
            apr.setCargaHorariaAprovada(cargaPleiteada);
            apr.setStatus(StatusAproveitamento.APROVADO);
            return apr;

        }
        throw new IllegalArgumentException("Usuário sem permissão para essa ação!");
    }

    public boolean indeferir(String id, Usuario u){
        if (UsuarioService.hasPermissao(u, Papel.COORDENADOR)){
            Aproveitamento apr = mapaAproveitamentos.get(id);
            if (apr.getStatus() == StatusAproveitamento.PENDENTE) {
                apr.setStatus(StatusAproveitamento.INDEFERIDO);
                return true;
            }
        }
        throw new IllegalArgumentException("Usuário sem permissão para essa ação!");
    }

    public Aproveitamento reenviar(String id){
        Aproveitamento apr = mapaAproveitamentos.get(id);
        if(apr.getStatus() == StatusAproveitamento.INDEFERIDO){
            apr.setStatus(StatusAproveitamento.PENDENTE);
            return apr;
        }
        throw new IllegalArgumentException("A inscrição não está indeferida!");
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
