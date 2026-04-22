package br.ufma.extensao.servicos;

import br.ufma.extensao.entidades.Discente;
import br.ufma.extensao.entidades.Inscricao;
import br.ufma.extensao.entidades.Oportunidade;
import br.ufma.extensao.enums.StatusInscricao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InscricaoService {
    private List<Inscricao> inscricoes = new ArrayList<>(); //todo trocar o formato de todos os ids p/ string TIP + 00 + proximoId
    private int proximoId = 1;

    public Inscricao inscrever(Discente discente, Oportunidade oportunidade, String motivacao){

        if (discente == null || oportunidade == null || motivacao == null)
            throw new IllegalArgumentException("Discente, oportunidade e motivação são obrigatórios.");

        String id = ("INS00" + proximoId);
        proximoId++;
        Inscricao inscricao = new Inscricao(id, discente, oportunidade, motivacao);
        inscricoes.add(inscricao);
        return inscricao;
    }

    public Inscricao aprovar(String inscricaoId){
        for (Inscricao i : inscricoes){
            if (i.getId().equals(inscricaoId)){
                if (i.getStatus() == StatusInscricao.PENDENTE){
                    i.setStatus(StatusInscricao.APROVADA);
                    return i;}
            }
        }
        return null;
    }

    public Inscricao rejeitar(String inscricaoId){
        for (Inscricao i : inscricoes){
            if (i.getId().equals(inscricaoId)){
                if (i.getStatus() == StatusInscricao.PENDENTE){
                    i.setStatus(StatusInscricao.REJEITADA);
                    return i;}
            }
        }
        return null;
    }

    public Inscricao cancelar(String inscricaoId){
        for (Inscricao i : inscricoes){
            if (i.getId().equals(inscricaoId)){
                if (i.getStatus() == StatusInscricao.APROVADA){
                    i.setStatus(StatusInscricao.CANCELADA);
                    return i;}
            }
        }
        return null;
    }

    public List <Inscricao> listarPorOportunidade(Oportunidade op){
        List<Inscricao> resultado = new ArrayList<>();
        for (Inscricao i : inscricoes){
            if(i.getOportunidade().equals(op)){
                resultado.add(i);
            }
        }
        return resultado; }

    public List <Inscricao> listarPorDiscente(Discente d){
        List<Inscricao> resultado = new ArrayList<>();
        for (Inscricao i : inscricoes){
            if(i.getDiscente().equals(d)){
                resultado.add(i);
            }
        }
        return resultado;
    }
}
