package br.ufma.extensao.servicos;

import br.ufma.extensao.entidades.Aproveitamento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AproveitamentoService {
    private List<Aproveitamento> aproveitamentos = new ArrayList<>();
    private int proximoId = 1;

    public Aproveitamento submeter(String discenteId, String descricao, double cargaHoraria){
        if (discenteId == null || descricao == null || cargaHoraria <= 0)
            throw new IllegalArgumentException("Dados do aproveitamento são inválidos!");
        String id = ("APR00" + proximoId);
        proximoId++;
        Aproveitamento aproveitamento = new Aproveitamento(id, discenteId, descricao, cargaHoraria);
        return aproveitamento;
    }

    public Aproveitamento aprovar(){}

    public Aproveitamento indeferir(){}

    public Aproveitamento reenviar(){}

    public List <Aproveitamento> (){}

    public List <Aproveitamento> (){}
}
