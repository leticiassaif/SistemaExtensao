package br.ufma.extensao.servicos;

import br.ufma.extensao.entidades.Discente;
import br.ufma.extensao.entidades.Inscricao;
import br.ufma.extensao.entidades.Usuario;
import br.ufma.extensao.enums.Papel;
import br.ufma.extensao.enums.StatusOportunidade;
import br.ufma.extensao.servicos.UsuarioService;
import br.ufma.extensao.entidades.Oportunidade;
import br.ufma.extensao.enums.StatusInscricao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InscricaoService {
    private List<Inscricao> inscricoes = new ArrayList<>();


    public Inscricao inscrever(Discente discente, Oportunidade oportunidade, String motivacao)
    {
        if (discente == null || oportunidade == null || motivacao == null) {
            throw new IllegalArgumentException("Dados obrigatórios ausentes.");
        }

        if (oportunidade.getStatus() != StatusOportunidade.ABERTA) {
            throw new IllegalStateException("Oportunidade não está aberta para inscrições.");
        }

        long ativas = inscricoes.stream().filter(i -> i.getOportunidade().equals(oportunidade) && i.getStatus() != StatusInscricao.CANCELADA).count();

        if (ativas >= oportunidade.getVagas()) {
            throw new IllegalStateException("Vagas esgotadas.");
        }

        boolean duplicada = inscricoes.stream().anyMatch(i -> i.getDiscente().equals(discente) && i.getOportunidade().equals(oportunidade));

        if (duplicada) {
            throw new IllegalStateException("Discente já inscrito nesta oportunidade.");
        }

        String id = "INS-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Inscricao inscricao = new Inscricao(id, discente, oportunidade, motivacao);
        inscricoes.add(inscricao);
        return inscricao;
    }

    public Inscricao aprovar(String inscricaoId, Usuario u){
        if (UsuarioService.hasPermissao(u, Papel.ADMIN) || UsuarioService.hasPermissao(u, Papel.DOCENTE)) {
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
        if (UsuarioService.hasPermissao(u, Papel.ADMIN) || UsuarioService.hasPermissao(u, Papel.DOCENTE)) {
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

    public Inscricao cancelar(String inscricaoId, Usuario u) {
        if (!UsuarioService.hasPermissao(u, Papel.DISCENTE)
                && !UsuarioService.hasPermissao(u, Papel.DISCENTE_DIRETOR))
            return null;
        for (Inscricao i : inscricoes) {
            if (!i.getId().equals(inscricaoId)) continue;
            if (!LocalDate.now().isBefore(i.getOportunidade().getInicio()))
                throw new IllegalStateException("Não é possível cancelar após o início da oportunidade.");
            if (i.getStatus() != StatusInscricao.APROVADA)
                return null;
            i.setStatus(StatusInscricao.CANCELADA);
            return i;
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
