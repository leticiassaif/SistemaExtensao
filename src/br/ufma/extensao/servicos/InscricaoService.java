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
    private long proximoId = 1;

    public Inscricao inscrever(Discente discente, Oportunidade oportunidade, String motivacao){

        if (discente == null || oportunidade == null || motivacao == null)
            throw new IllegalArgumentException("Discente, oportunidade e motivação são obrigatórios.");

        Long id = proximoId++;
        Inscricao inscricao = new Inscricao(id, discente, oportunidade, motivacao);
        inscricoes.add(inscricao);
        return inscricao;
    }

    public Inscricao aprovar(Long inscricaoId){
        for (Inscricao i : inscricoes){
            if (i.getId().equals(inscricaoId)){
                if (i.getStatus() == StatusInscricao.PENDENTE){
                    i.setStatus(StatusInscricao.APROVADA);
                    return i;}
            }
        }
        return null;
    }

    public Inscricao rejeitar(Long inscricaoId){
        for (Inscricao i : inscricoes){
            if (i.getId().equals(inscricaoId)){
                if (i.getStatus() == StatusInscricao.PENDENTE){
                    i.setStatus(StatusInscricao.REJEITADA);
                    return i;}
            }
        }
        return null;
    }

    public Inscricao cancelar(Long inscricaoId){
        for (Inscricao i : inscricoes){
            if (i.getId().equals(inscricaoId)){
                if (i.getStatus() != StatusInscricao.CANCELADA){
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
