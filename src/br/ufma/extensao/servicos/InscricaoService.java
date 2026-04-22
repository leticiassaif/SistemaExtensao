package br.ufma.extensao.servicos;

import br.ufma.extensao.entidades.Discente;
import br.ufma.extensao.entidades.Inscricao;
import br.ufma.extensao.entidades.Oportunidade;
import br.ufma.extensao.enums.StatusInscricao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InscricaoService {
    private List<Inscricao> inscricoes = new ArrayList<>();

    public Inscricao inscrever(Discente discenteId, Oportunidade oportunidadeId, String motivacao){
        Long id = (long) (inscricoes.size() + 1);
        LocalDate dataInscricao = LocalDate.now();
        StatusInscricao status = StatusInscricao.PENDENTE;
        return new Inscricao(id, discenteId, oportunidadeId, motivacao, dataInscricao, status);
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

    //todo descobrir como ordenar essas listas
    public List <Inscricao> listarPorOportunidade(){
            return inscricoes; }

    public List <Inscricao> listarPorDiscente(){ return inscricoes; }
}
