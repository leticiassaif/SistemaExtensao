package br.ufma.extensao.servicos;

import br.ufma.extensao.entidades.Discente;
import br.ufma.extensao.entidades.Inscricao;
import br.ufma.extensao.entidades.Usuario;
import br.ufma.extensao.enums.Papel;
import br.ufma.extensao.servicos.UsuarioService;
import br.ufma.extensao.entidades.Oportunidade;
import br.ufma.extensao.enums.StatusInscricao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InscricaoService {
    private List<Inscricao> inscricoes = new ArrayList<>();
    //todo: colocar map

    public Inscricao inscrever(Discente discente, Oportunidade oportunidade, String motivacao){

        if (discente == null || oportunidade == null || motivacao == null)
            throw new IllegalArgumentException("Discente, oportunidade e motivação são obrigatórios.");

        String id = "INS-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Inscricao inscricao = new Inscricao(id, discente, oportunidade, motivacao);
        inscricoes.add(inscricao);
        return inscricao;
    }

    public Inscricao aprovar(String inscricaoId, Usuario u){
        if (UsuarioService.hasPermissao(u, Papel.ADMIN) || UsuarioService.hasPermissao(u, Papel.DOCENTE) || UsuarioService.hasPermissao(u, Papel.DISCENTE_DIRETOR)) {
            for (Inscricao i : inscricoes) {
                if (i.getId().equals(inscricaoId)) {
                    if (i.getStatus() == StatusInscricao.PENDENTE) {
                        i.setStatus(StatusInscricao.APROVADA);
                        return i;
                    }
                }
            }
        }
        return null;
    }

    public Inscricao rejeitar(String inscricaoId, Usuario u){
        if (UsuarioService.hasPermissao(u, Papel.ADMIN) || UsuarioService.hasPermissao(u, Papel.DOCENTE) || UsuarioService.hasPermissao(u, Papel.DISCENTE_DIRETOR)) {
            for (Inscricao i : inscricoes) {
                if (i.getId().equals(inscricaoId)) {
                    if (i.getStatus() == StatusInscricao.PENDENTE) {
                        i.setStatus(StatusInscricao.REJEITADA);
                        return i;
                    }
                }
            }
        }
        return null;
    }

    public Inscricao cancelar(String inscricaoId, Usuario u){
        if (UsuarioService.hasPermissao(u, Papel.DISCENTE) || UsuarioService.hasPermissao(u, Papel.DISCENTE_DIRETOR)) {
            for (Inscricao i : inscricoes) {
                if (i.getId().equals(inscricaoId) && LocalDate.now().isBefore(i.getOportunidade().getInicio())) {
                    if (i.getStatus() == StatusInscricao.APROVADA) {
                        i.setStatus(StatusInscricao.CANCELADA);
                        return i;
                    }
                if (!(LocalDate.now().isBefore(i.getOportunidade().getInicio())))
                        throw new IllegalArgumentException("Não é possível cancelar a inscrição em oportunidades já iniciadas!");
                }
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
