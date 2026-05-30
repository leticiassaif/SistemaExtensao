package br.ufma.extensao.servicos;

import br.ufma.extensao.entidades.Curso;
import br.ufma.extensao.entidades.PPC;
import br.ufma.extensao.entidades.Usuario;
import br.ufma.extensao.enums.Papel;

import java.time.LocalDate;
import java.util.*;

import static br.ufma.extensao.servicos.UsuarioService.hasPermissao;

public class PPCService {

    //Deque é uma pilha
    private Map<Curso, Deque<PPC>> historicoPPC = new HashMap<>();

    public PPC criarPPC(Usuario solicitante, Curso curso , String versao, Double cargaHoraria) {
        if (!(hasPermissao(solicitante, Papel.COORDENADOR) || hasPermissao(solicitante, Papel.ADMIN))) {
            throw new IllegalStateException("O Solicitante não possui acesso a essa função");
        }

        if (curso == null) {
            throw new IllegalArgumentException("Curso é obrigatório");
        }

        if (versao == null || versao.isBlank()) {
            throw new IllegalArgumentException("Descrição é obrigatória.");
        }

        if (cargaHoraria <= 0) {
            throw new IllegalArgumentException("Carga Horária deve ser positiva");
        }

        String id = "PPC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        PPC ppc = new PPC(id, curso.getId(), versao, cargaHoraria);

        Deque<PPC> pilha = historicoPPC.get(curso);

        if(pilha != null && !pilha.isEmpty()) {
            //tem ppc
            pilha.peek().setDataFim(LocalDate.now()); //encerra o atual
            pilha.push(ppc); // começa o prox
        } else {
            Deque<PPC> novaPilha = new ArrayDeque<>();
            novaPilha.push(ppc);
            historicoPPC.put(curso, novaPilha);
        }

        return ppc;
    }
}
