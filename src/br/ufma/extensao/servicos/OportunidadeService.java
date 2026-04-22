package br.ufma.extensao.servicos;

import br.ufma.extensao.enums.Modalidade;
import br.ufma.extensao.enums.StatusOportunidade;
import br.ufma.extensao.enums.TipoOportunidade;
import br.ufma.extensao.entidades.Docente;
import br.ufma.extensao.entidades.Oportunidade;
import br.ufma.extensao.entidades.Usuario;

import br.ufma.extensao.enums.Modalidade;
import br.ufma.extensao.enums.Papel;
import br.ufma.extensao.enums.Cargo;
import br.ufma.extensao.enums.StatusOportunidade;
import br.ufma.extensao.enums.TipoOportunidade;
import br.ufma.extensao.entidades.Docente;
import br.ufma.extensao.entidades.Oportunidade;
import br.ufma.extensao.entidades.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OportunidadeService {
    List<Oportunidade> oportunidades = new ArrayList<>();


    public Oportunidade criarOportunidade(String titulo, String descricao, TipoOportunidade tipo, Modalidade modalidade, int cargaHoraria, int vagas, Docente responsavelId, Usuario autor, LocalDate inicio, LocalDate fim){
        if (autor.getPapel() == Papel.DOCENTE || autor.getCargo() == Cargo.DIRETOR) {
            Oportunidade oportunidade = new Oportunidade( titulo, descricao, tipo, modalidade, cargaHoraria, vagas, StatusOportunidade.RASCUNHO, inicio, fim, autor, responsavelId);
            oportunidades.add(oportunidade);
            return oportunidade;
        }
        return null;
    }

    public Oportunidade publicarOportunidade(Long id, Usuario autor){
        for (Oportunidade op : oportunidades){
            if (op.getId().equals(id)) {
                if (autor.getCargo() == Cargo.DIRETOR) {
                    op.setStatus(StatusOportunidade.AGUARDANDO_APROVACAO);
                    return op;
                }
                if (autor.getPapel() == Papel.DOCENTE) {
                    op.setStatus(StatusOportunidade.ABERTA);
                    return op;
                }
            }
        }
        return null;
    }

    public Oportunidade aprovarOportunidade(Long id, Usuario usuario){
        for (Oportunidade op : oportunidades){
            if (op.getId().equals(id)) {
                if (usuario.getPapel() == Papel.DOCENTE) {
                    if (op.getStatus() == StatusOportunidade.AGUARDANDO_APROVACAO) {
                        op.setStatus(StatusOportunidade.ABERTA);
                        return op;
                    }
                }
            }
        }
        return null;
    }

    public Oportunidade iniciarExecucao(Long id){
        for (Oportunidade op : oportunidades){
            if (op.getId().equals(id)) {
                op.setStatus(StatusOportunidade.EM_EXECUCAO);
                return op;
            }
        }
        return null;
    }

    public Oportunidade encerrarOportunidade(Long id){
        for (Oportunidade op : oportunidades){
            if (op.getId().equals(id)) {
                op.setStatus(StatusOportunidade.ENCERRADA);
                return op;
            }
        }
        return null;
    }

    public Oportunidade cancelarOportunidade(Long id){
        for (Oportunidade op : oportunidades){
            if (op.getId().equals(id)) {
                op.setStatus(StatusOportunidade.CANCELADA);
                return op;
            }
        }
        return null;
    }

    public Oportunidade buscarOportunidadePorId(Long id){
        for (Oportunidade op : oportunidades){
            if (op.getId().equals(id)) {
                return op;
            }
        }
        return null;
    }

    private List <Oportunidade> listarOportunidades(){
        return oportunidades;
    }
}
