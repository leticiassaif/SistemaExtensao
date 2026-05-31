package br.ufma.extensao.servicos;

import br.ufma.extensao.entidades.Discente;
import br.ufma.extensao.entidades.Inscricao;
import br.ufma.extensao.entidades.Usuario;
import br.ufma.extensao.enums.Papel;
import br.ufma.extensao.enums.StatusOportunidade;
import br.ufma.extensao.entidades.Oportunidade;
import br.ufma.extensao.enums.StatusInscricao;

import java.time.LocalDate;
import java.util.*;

public class InscricaoService {
    private Map<Oportunidade, List<Inscricao>> inscricoes = new HashMap<>();


    public Inscricao inscrever(Discente discente, Oportunidade oportunidade, String motivacao) {
        if (discente == null || oportunidade == null || motivacao == null) {
            throw new IllegalArgumentException("Dados obrigatórios ausentes.");
        }

        if (oportunidade.getStatus() != StatusOportunidade.ABERTA) {
            throw new IllegalStateException("Oportunidade não está aberta para inscrições.");
        }

        List<Inscricao> fila = inscricoes.get(oportunidade);

        if (fila != null) {
            for (Inscricao inscricoes : fila) {
                if ( inscricoes.getDiscente().equals(discente)) {
                    throw new IllegalStateException("O usuário " + discente.getNome() + " já foi inscrito na oportunidade");
                }
            }
        }

        String id = "INS-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Inscricao inscricao = new Inscricao(id, discente, oportunidade, motivacao);

        inscricoes.computeIfAbsent(oportunidade, k -> new ArrayList<>()).add(inscricao);

        return inscricao;

    }

    private List<Inscricao> contarAprovadas(Oportunidade oportunidade) {

        if (oportunidade == null ) {
            throw new IllegalArgumentException("Campos obrigatórios não foram informados");
        }

        List<Inscricao> fila = inscricoes.get(oportunidade);

        if (fila == null) {
            return new ArrayList<>();
        }

        List<Inscricao> resultado = new ArrayList<>();

        for (Inscricao inscricao : fila) {
            if (inscricao.getStatus().equals(StatusInscricao.APROVADA)) {
                resultado.add(inscricao);
            }
        }
        return resultado;
    }

    private Inscricao buscarIncricao(String inscricaoId, Oportunidade oportunidade) {
        if (oportunidade == null ) {
            throw new IllegalArgumentException("Campos obrigatórios não foram informados");
        }

        if (inscricaoId == null || inscricaoId.isBlank()) {
            throw new IllegalArgumentException("O ID da Inscrição não foi informado");
        }

        List<Inscricao> fila = inscricoes.get(oportunidade);

        if (fila == null || fila.isEmpty()) {
            throw new NoSuchElementException("Nenhuma inscrição encontrada para essa oportunidade");
        }

        for (Inscricao inscricao : fila) {
            if (inscricao.getId().equals(inscricaoId)) {
                return inscricao;
            }
        }

        throw new NoSuchElementException("A inscrição não foi achada");
    }


    public Inscricao aprovar(String inscricaoId, Oportunidade oportunidade, Usuario solicitante){

        if (solicitante == null) {
            throw new IllegalArgumentException("O Solicitante deve ser informado");
        }

        if (inscricaoId == null || inscricaoId.isBlank()) {
            throw new IllegalArgumentException("O ID da Inscrição não foi informado");
        }

        boolean autor = solicitante.getId().equals(oportunidade.getAutor().getId());
        boolean docenteResponsavel = solicitante.getId().equals(oportunidade.getDocenteResponsavelId());

        if (!(autor || docenteResponsavel || solicitante.getPapel().equals(Papel.ADMIN))) {
            throw new IllegalStateException("O Solicitante deve ser o responsável pela Oportunidade");
        }

        if (contarAprovadas(oportunidade).size() >= oportunidade.getVagas()) {
            throw new IllegalStateException("Vagas esgotadas");
        }

        Inscricao inscricao = buscarIncricao(inscricaoId, oportunidade);
        inscricao.setStatus(StatusInscricao.APROVADA);
        return inscricao;

    }

    public Inscricao rejeitar(String inscricaoId, Oportunidade oportunidade, Usuario solicitante){

        if (solicitante == null) {
            throw new IllegalArgumentException("O Solicitante deve ser informado");
        }

        if (inscricaoId == null || inscricaoId.isBlank()) {
            throw new IllegalArgumentException("O ID da Inscrição não foi informado");
        }

        boolean autor = solicitante.getId().equals(oportunidade.getAutor().getId());
        boolean docenteResponsavel = solicitante.getId().equals(oportunidade.getDocenteResponsavelId());

        if (!(autor || docenteResponsavel || solicitante.getPapel().equals(Papel.ADMIN))) {
            throw new IllegalStateException("O Solicitante deve ser o responsável pela Oportunidade");
        }

        Inscricao inscricao = buscarIncricao(inscricaoId, oportunidade);
        inscricao.setStatus(StatusInscricao.REJEITADA);
        promoverFilaEspera(oportunidade);
        return inscricao;
    }

    public Inscricao desistir(String inscricaoId, Oportunidade oportunidade, Usuario solicitante) {

        if (inscricaoId == null || inscricaoId.isBlank()) {
            throw new IllegalArgumentException("O ID da Inscrição é inválido");
        }

        Inscricao incricao = buscarIncricao(inscricaoId, oportunidade);

        boolean autorIsDiscente = solicitante.getId().equals(incricao.getDiscente().getId());

        if (!autorIsDiscente) {
            throw new IllegalStateException("Apenas o próprio discente pode desistir");
        }

        incricao.setStatus(StatusInscricao.CANCELADA);
        promoverFilaEspera(oportunidade);
        return incricao;

    }

    private void promoverFilaEspera(Oportunidade oportunidade) {

        if (oportunidade == null) {
            throw new IllegalArgumentException("A Oportunidade não existe");
        }

        List<Inscricao> fila = inscricoes.get(oportunidade);

        if (fila == null || fila.isEmpty()) {
            return;
        }

        fila.sort(Comparator.comparing(Inscricao::getDataInscricao));

        if (contarAprovadas(oportunidade).size() < oportunidade.getVagas()) {
            for (Inscricao inscricao : fila) {
                if (inscricao.getStatus() == StatusInscricao.PENDENTE) {
                    return;
                }
            }
        }

    }


    public List <Inscricao> listarPorOportunidade(Oportunidade oportunidade){
        if (oportunidade == null) {
            throw new IllegalArgumentException("Oportunidade é obrigatória");
        }

        List<Inscricao> fila = inscricoes.get(oportunidade);

        if (fila == null) {
            return new ArrayList<>();  // retorna lista vazia em vez de null
        }

        return fila;
    }

    public List<Inscricao> listarPorDiscente(Discente discente) {
        if (discente == null) {
            throw new IllegalArgumentException("Discente é obrigatório");
        }

        List<Inscricao> resultado = new ArrayList<>();

        for (List<Inscricao> fila : inscricoes.values()) {
            for (Inscricao inscricao : fila) {
                if (inscricao.getDiscente().equals(discente)) {
                    resultado.add(inscricao);
                }
            }
        }

        return resultado;
    }
}
