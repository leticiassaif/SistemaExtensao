package br.ufma.extensao.servicos;

import br.ufma.extensao.enums.Modalidade;
import br.ufma.extensao.enums.StatusOportunidade;
import br.ufma.extensao.enums.TipoOportunidade;
import br.ufma.extensao.entidades.Oportunidade;
import br.ufma.extensao.entidades.Usuario;
import br.ufma.extensao.enums.Papel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OportunidadeService {
    private List<Oportunidade> oportunidades = new ArrayList<>();

    public Oportunidade criarOportunidade(String titulo, String descricao, TipoOportunidade tipo, Modalidade modalidade, int cargaHoraria, int vagas, String responsavelId, Usuario autor, LocalDate inicio, LocalDate fim){
        if (titulo == null || titulo.isBlank())
            throw new IllegalArgumentException("Título é obrigatório.");
        if (descricao == null || descricao.isBlank())
            throw new IllegalArgumentException("Descrição é obrigatória.");
        if (cargaHoraria <= 0)
            throw new IllegalArgumentException("Carga horária deve ser positiva.");
        if (inicio == null || fim == null || fim.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Datas inválidas.");

        if (!(autor.getPapel() == Papel.DOCENTE || autor.getPapel() == Papel.DISCENTE_DIRETOR)) {
            throw new IllegalArgumentException("Usuário não tem permissão para criar oportunidade!");
            }
        // Criar a oportunidade diretamente, sem o segundo if redundante:
        String id = "OPT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Oportunidade oportunidade = new Oportunidade(id, titulo, descricao, tipo, modalidade, cargaHoraria, vagas, responsavelId, inicio, fim, autor);
        oportunidades.add(oportunidade);
        return oportunidade;
    }

    public Oportunidade publicarOportunidade(String id, Usuario autor){
        for (Oportunidade op : oportunidades){
            if (op.getId().equals(id)) {
                if (autor.getPapel() == Papel.DISCENTE_DIRETOR) {
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

    public Oportunidade iniciarExecucao(String id) {
        for (Oportunidade op : oportunidades) {
            if (op.getId().equals(id) && !LocalDate.now().isBefore(op.getInicio()) && op.getStatus() == StatusOportunidade.ABERTA) {
                op.setStatus(StatusOportunidade.EM_EXECUCAO);
                return op;
            }
        }
        return null;
    }

    public Oportunidade encerrarOportunidade(String id) {
        for (Oportunidade op : oportunidades) {
            if (op.getId().equals(id) && !LocalDate.now().isBefore(op.getFim()) && op.getStatus() == StatusOportunidade.EM_EXECUCAO) {
                op.setStatus(StatusOportunidade.ENCERRADA);
                return op;
            }
        }
        return null;
    }

    public Oportunidade cancelarOportunidade(String id, Usuario u){
        if (UsuarioService.hasPermissao(u, Papel.ADMIN) || UsuarioService.hasPermissao(u, Papel.DOCENTE)) {
            for (Oportunidade op : oportunidades) {
                if (op.getId().equals(id)) {
                    op.setStatus(StatusOportunidade.CANCELADA);
                    return op;
                }
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
