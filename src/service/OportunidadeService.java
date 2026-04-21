package service;

import enums.Modalidade;
import enums.Papel;
import enums.StatusOportunidade;
import enums.TipoOportunidade;
import model.Docente;
import model.Oportunidade;
import model.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OportunidadeService {
    List<Oportunidade> oportunidades = new ArrayList<>();

    Oportunidade criarOportunidade(String titulo, String descricao, TipoOportunidade tipo, Modalidade modalidade, int cargaHoraria, int vagas, Docente responsavelId, Usuario autor, LocalDate inicio, LocalDate fim){
        Oportunidade oportunidade = new Oportunidade(titulo, descricao, tipo, modalidade, cargaHoraria, vagas, StatusOportunidade.PENDENTE, inicio, fim, autor, responsavelId);
        oportunidades.add(oportunidade);
        return oportunidade;
    }

    Oportunidade publicarOportunidade(String titulo){
        for (Oportunidade op : oportunidades){
            if (op.getTitulo().equals(titulo)) {
                op.setStatus(StatusOportunidade.PUBLICADA);
                return op;
            }
        }
        return null;
    }

    Oportunidade iniciarExecucao(String titulo){
        for (Oportunidade op : oportunidades){
            if (op.getTitulo().equals(titulo)) {
                op.setStatus(StatusOportunidade.EM_PROGRESSO);
                return op;
            }
        }
        return null;
    }

    Oportunidade encerrarOportunidade(String titulo){
        for (Oportunidade op : oportunidades){
            if (op.getTitulo().equals(titulo)) {
                op.setStatus(StatusOportunidade.ENCERRADA);
                return op;
            }
        }
        return null;
    }

    Oportunidade cancelarOportunidade(String titulo){
        for (Oportunidade op : oportunidades){
            if (op.getTitulo().equals(titulo)) {
                op.setStatus(StatusOportunidade.CANCELADA);
                return op;
            }
        }
        return null;
    }

    Oportunidade buscarOportunidadePorTitulo(String titulo){
        for (Oportunidade op : oportunidades){
            if (op.getTitulo().equals(titulo)) {;
                return op;
            }
        }
        return null;
    }

    List <Oportunidade> listarOportunidades(){
        return oportunidades;
    }
}
