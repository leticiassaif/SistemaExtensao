package br.ufma.extensao.servicos;

import br.ufma.extensao.entidades.Discente;
import br.ufma.extensao.entidades.Inscricao;
import br.ufma.extensao.entidades.Usuario;
import br.ufma.extensao.enums.Papel;
import br.ufma.extensao.enums.StatusOportunidade;
import br.ufma.extensao.entidades.Oportunidade;
import br.ufma.extensao.enums.StatusInscricao;

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

        if (listarSlotsOcupados(oportunidade).size() >= oportunidade.getVagas()) {
            inscricao.setStatus(StatusInscricao.LISTA_DE_ESPERA);
        }

        inscricoes.computeIfAbsent(oportunidade, k -> new ArrayList<>()).add(inscricao);

        return inscricao;

    }

    private List<Inscricao> listarSlotsOcupados(Oportunidade oportunidade) {

        if (oportunidade == null ) {
            throw new IllegalArgumentException("Campos obrigatórios não foram informados");
        }

        List<Inscricao> fila = inscricoes.get(oportunidade);

        if (fila == null) {
            return new ArrayList<>();
        }

        List<Inscricao> resultado = new ArrayList<>();

        for (Inscricao inscricao : fila) {
            if (inscricao.getStatus().equals(StatusInscricao.APROVADA) || inscricao.getStatus().equals(StatusInscricao.PENDENTE)) {
                resultado.add(inscricao);
            }
        }
        return resultado;
    }

    private Inscricao buscarInscricao(String inscricaoId, Oportunidade oportunidade) {
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

        int aprovadas = 0;
        for (Inscricao i : listarSlotsOcupados(oportunidade)) {
            if (i.getStatus().equals(StatusInscricao.APROVADA)) {
                aprovadas++;
            }
        }

        if (aprovadas >= oportunidade.getVagas()) {
            throw new IllegalStateException("Vagas esgotadas");
        }

        Inscricao inscricao = buscarInscricao(inscricaoId, oportunidade);

        if (!inscricao.getStatus().equals(StatusInscricao.PENDENTE)) {
            throw new IllegalStateException("Só é possível aprovar inscrições PENDENTES");
        }

        inscricao.setStatus(StatusInscricao.APROVADA);
        return inscricao;

    }

    public Inscricao rejeitarRemoverDiscente(String inscricaoId, String justificativa, Oportunidade oportunidade, Usuario solicitante){

        if (solicitante == null) {
            throw new IllegalArgumentException("O Solicitante deve ser informado");
        }

        if (inscricaoId == null || inscricaoId.isBlank()) {
            throw new IllegalArgumentException("O ID da Inscrição não foi informado");
        }

        if (justificativa == null || justificativa.isBlank()) {
            throw new IllegalArgumentException("A justificativa não foi informada");
        }

        boolean autor = solicitante.getId().equals(oportunidade.getAutor().getId());
        boolean docenteResponsavel = solicitante.getId().equals(oportunidade.getDocenteResponsavelId());

        if (!(autor || docenteResponsavel || solicitante.getPapel().equals(Papel.ADMIN))) {
            throw new IllegalStateException("O Solicitante deve ser o responsável pela Oportunidade");
        }

        Inscricao inscricao = buscarInscricao(inscricaoId, oportunidade);

        if(inscricao.getStatus().equals(StatusInscricao.APROVADA)) {
            inscricao.setStatus(StatusInscricao.CANCELADA);
        } else if (inscricao.getStatus().equals(StatusInscricao.PENDENTE)) {
            inscricao.setStatus(StatusInscricao.REJEITADA);
        } else {
            throw new IllegalStateException("Só é possível rejeitar inscrições PENDENTES ou remover participantes APROVADOS");
        }

        inscricao.setJustificativaCancelamento(justificativa);
        promoverFilaEspera(oportunidade);
        return inscricao;
    }

    public Inscricao desistir(String inscricaoId, Oportunidade oportunidade, Usuario solicitante) {

        if (inscricaoId == null || inscricaoId.isBlank()) {
            throw new IllegalArgumentException("O ID da Inscrição é inválido");
        }

        Inscricao incricao = buscarInscricao(inscricaoId, oportunidade);

        if (incricao.getStatus().equals(StatusInscricao.REJEITADA) || incricao.getStatus().equals(StatusInscricao.CANCELADA)) {
            throw new IllegalStateException("A inscrição já está rejeitada ou cancelada");
        }

        boolean autorIsDiscente = solicitante.getId().equals(incricao.getDiscente().getId());

        if (!autorIsDiscente) {
            throw new IllegalStateException("Apenas o próprio discente pode desistir");
        }

        incricao.setStatus(StatusInscricao.CANCELADA);
        incricao.setJustificativaCancelamento("O Discente desistiu da vaga");
        promoverFilaEspera(oportunidade);
        return incricao;

    }

    private void promoverFilaEspera(Oportunidade oportunidade) {

        if (oportunidade == null) {
            throw new IllegalArgumentException("A Oportunidade não existe");
        }

        List<Inscricao> espera = listarFilaEspera(oportunidade);

        if (!espera.isEmpty()) {
            espera.getFirst().setStatus(StatusInscricao.PENDENTE);
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

    public List<Inscricao> listarFilaEspera(Oportunidade oportunidade) {
        if (oportunidade == null) {
            throw new IllegalArgumentException("Oportunidade é obrigatória");
        }

        List<Inscricao> fila = inscricoes.get(oportunidade);

        if (fila == null) {
            return new ArrayList<>();
        }

        List<Inscricao> resultado = new ArrayList<>();
        fila.sort(Comparator.comparing(Inscricao::getDataInscricao));

        for (Inscricao inscricao : fila) {
            if (inscricao.getStatus().equals(StatusInscricao.LISTA_DE_ESPERA)) {
                resultado.add(inscricao);
            }
        }

        return resultado;
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
