package br.ufma.extensao.servicos;

import br.ufma.extensao.entidades.Curso;
import br.ufma.extensao.entidades.Oportunidade;
import br.ufma.extensao.entidades.PPC;
import br.ufma.extensao.entidades.Usuario;
import br.ufma.extensao.enums.Papel;

import java.time.LocalDate;
import java.util.*;

import static br.ufma.extensao.servicos.UsuarioService.hasPermissao;

public class PPCService {


    // TODO: RF007 - Permissão
    // Atualmente só verifica COORDENADOR, mas G-25 permite Admin OU Coordenador.
    // Corrigir para:
    // if (!hasPermissao(solicitante, Papel.COORDENADOR) && !hasPermissao(solicitante, Papel.ADMIN))

    //Deque é uma pilha
    private Map<Curso, Deque<PPC>> historicoPPC = new HashMap<>();

    public PPC criarPPC(Usuario solicitante, String cursoId , String versao, Double cargaHoraria, LocalDate dataInicio) {
        if (!(hasPermissao(solicitante, Papel.COORDENADOR) || hasPermissao(solicitante, Papel.ADMIN))) {
            throw new IllegalStateException("O Solicitante não possui acesso a essa função");
        }

        if (cursoId == null || cursoId.isBlank()) {
            throw new IllegalArgumentException("Título é obrigatório.");
        }

        if (versao == null || versao.isBlank()) {
            throw new IllegalArgumentException("Descrição é obrigatória.");
        }

        if (cargaHoraria < 0) {
            throw new IllegalArgumentException("Carga Horária deve ser positiva");
        }

        String id = "OPT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        PPC ppc = new PPC(id, cursoId, versao, cargaHoraria);


        // TODO: RF008 - Histórico de versões
        // Ao adicionar novo PPC, o anterior deve ter dataFim setada para hoje.
        // Passos:
        // 1. Verificar se já existe uma pilha para esse curso em historicoPPC
        // 2. Se existir e não estiver vazia, pegar o topo (peek) e setar dataFim = LocalDate.now()
        // 3. Empilhar o novo PPC com push
        // 4. Se não existir, criar um novo ArrayDeque, empilhar o PPC e adicionar ao Map

        //historicoPPC.computeIfAbsent(curso, k -> new ArrayDeque<>()).push(ppc);

        return ppc;
    }
}
