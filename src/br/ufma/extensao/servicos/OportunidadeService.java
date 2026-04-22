package br.ufma.extensao.servicos;

import br.ufma.extensao.enums.Modalidade;
import br.ufma.extensao.enums.StatusOportunidade;
import br.ufma.extensao.enums.TipoOportunidade;
import br.ufma.extensao.entidades.Oportunidade;
import br.ufma.extensao.entidades.Usuario;
import br.ufma.extensao.enums.Papel;
import br.ufma.extensao.enums.Cargo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OportunidadeService {
    private List<Oportunidade> oportunidades = new ArrayList<>();
    private int proximoId = 1;


    public Oportunidade criarOportunidade(String titulo, String descricao, TipoOportunidade tipo, Modalidade modalidade, int cargaHoraria, int vagas, Long responsavelId, Usuario autor, LocalDate inicio, LocalDate fim){
        if (titulo == null || descricao == null)
            throw new IllegalArgumentException("Discente e oportunidade são obrigatórios.");
        if (cargaHoraria <= 0)
            throw new IllegalArgumentException("Carga horária deve ser positiva.");
        if (inicio == null || fim == null || fim.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Datas inválidas.");
        if (autor.getPapel() == Papel.DOCENTE || autor.getCargo() == Cargo.DIRETOR) {  //todo problemas com os cargos

            String id = ("OPT00" + proximoId);
            proximoId++;
            Oportunidade oportunidade = new Oportunidade(id, titulo, descricao, tipo, modalidade, cargaHoraria, vagas, responsavelId, inicio, fim, autor);
            oportunidades.add(oportunidade);
            return oportunidade;
        }
        return null;
    }

    public Oportunidade publicarOportunidade(String id, Usuario autor){
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

    public Oportunidade aprovarOportunidade(String id, Usuario usuario){
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

    public Oportunidade iniciarExecucao(String id){
        for (Oportunidade op : oportunidades){
            if (op.getId().equals(id)) {
                op.setStatus(StatusOportunidade.EM_EXECUCAO);
                return op;
            }
        }
        return null;
    }

    public Oportunidade encerrarOportunidade(String id){
        for (Oportunidade op : oportunidades){
            if (op.getId().equals(id)) {
                op.setStatus(StatusOportunidade.ENCERRADA);
                return op;
            }
        }
        return null;
    }

    public Oportunidade cancelarOportunidade(String id){
        for (Oportunidade op : oportunidades){
            if (op.getId().equals(id)) {
                op.setStatus(StatusOportunidade.CANCELADA);
                return op;
            }
        }
        return null;
    }

    public Oportunidade buscarOportunidadePorId(String id){
        for (Oportunidade op : oportunidades){
            if (op.getId().equals(id)) {
                return op;
            }
        }
        return null;
    }

    public List <Oportunidade> listarOportunidades(){
        return oportunidades;
    }
}
