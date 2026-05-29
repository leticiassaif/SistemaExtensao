// Importação dos pacotes corretos do projeto
import br.ufma.extensao.enums.*;
import br.ufma.extensao.entidades.*;
import br.ufma.extensao.servicos.*;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

// PASSO 1 — INSTANCIAR TODOS OS SERVIÇOS
        System.out.println("Instanciando todos os serviços:");

        UsuarioService usuarioService       = new UsuarioService();
        InscricaoService inscricaoService   = new InscricaoService();
        CertificadoService certificadoService = new CertificadoService();
        OportunidadeService oportunidadeService = new OportunidadeService(inscricaoService, certificadoService);
        AproveitamentoService aprovService  = new AproveitamentoService();
        GrupoService grupoService           = new GrupoService();

        System.out.println("   Todos os serviços instanciados.\n");

// PASSO 2 — Cadastrar um Docente e um Discente
        System.out.println("Cadastrando Docente e Discente:");

        // Cadastra Docente passando: nome, email, senha, siape, departamento
        Docente docente = usuarioService.cadastrarDocente(
                "Prof. Carlos Silva",
                "carlos.silva@docente.ufma.br",
                "senha123",
                "1234567",
                "Departamento de Informática"
        );
        System.out.println("   Docente cadastrado : " + docente.getNome()
                + " | SIAPE: " + docente.getSiape()
                + " | Depto: " + docente.getDepartamento());

        // Cadastra Discente passando: nome, email, senha, matrícula, semestre, curso
        Curso cursoCC = new Curso("1L", "Ciência da Computação", "CC");
        Discente discente = usuarioService.cadastrarDiscente(
                "Ana Souza",
                "ana.souza@discente.ufma.br",
                "senha456",
                "20230001",
                3,
                cursoCC
        );
        System.out.println("   Discente cadastrado: " + discente.getNome()
                + " | Matrícula: " + discente.getMatricula()
                + " | Semestre: " + discente.getSemestreAtual()
                + " | Curso: " + discente.getCurso().getNome() + "\n");

// PASSO 3 — Cadastrar um Coordenador
        System.out.println("Cadastrando Coordenador:");

        // Coordenador é cadastrado com nome, email, senha, siape e cargo de coordenação
        Usuario coordenador = usuarioService.cadastrarCoordenador(
                "Coord. Mariana Lima",
                "mariana.lima@ufma.br",
                "senha789",
                "7654321",
                CargoCoordenador.COORDENADOR
        );
        System.out.println("   Coordenador cadastrado: " + coordenador.getNome()
                + " | Papel: " + coordenador.getPapel().getDescricao() + "\n");

// PASSO 4 — Criar uma oportunidade e publicá-la
        System.out.println("Criando e publicando oportunidade:");

        // criarOportunidade requer: titulo, descricao, tipo, modalidade,
        //   cargaHoraria, vagas, responsavelId (Long), autor, inicio, fim
        Oportunidade oportunidade = oportunidadeService.criarOportunidade(
                "Monitoria de Programação Orientada a Objetos",
                "Auxílio a alunos na disciplina de LP2",
                TipoOportunidade.PROJETO,
                Modalidade.PRESENCIAL,
                60,
                5,
                null,           // responsavelId  — não obrigatório nesta etapa
                docente,
                LocalDate.now().minusMonths(6),
                LocalDate.now()
        );
        System.out.println("   Oportunidade criada  : " + oportunidade.getTitulo()
                + " | Status: " + oportunidade.getStatus());

        // Publica a oportunidade (docente → status ABERTA diretamente)
        oportunidadeService.publicarOportunidade(oportunidade.getId(), docente);
        System.out.println("   Oportunidade publicada. Status: " + oportunidade.getStatus() + "\n");

// PASSO 5 — Inscrever o discente e aprovar a inscrição
        System.out.println("Inscrevendo discente e aprovando inscrição:");

        // inscrever requer: discente, oportunidade, motivacao
        Inscricao inscricao = inscricaoService.inscrever(
                discente,
                oportunidade,
                "reforçar meus conhecimentos"
        );
        System.out.println("   Inscrição realizada: " + discente.getNome()
                + " → " + oportunidade.getTitulo());

        // Aprova a inscrição (docente tem permissão)
        inscricaoService.aprovar(inscricao.getId(), docente);
        System.out.println("   Inscrição aprovada Status: " + StatusInscricao.APROVADA + "\n");

// PASSO 6 — Iniciar execução da oportunidade e encerrar
        System.out.println("Iniciando e encerrando execução da oportunidade:");

        oportunidadeService.iniciarExecucao(oportunidade.getId());
        System.out.println("   Oportunidade em andamento. Status: " + oportunidade.getStatus());

        oportunidadeService.encerrarOportunidade(oportunidade.getId());
        System.out.println("   Oportunidade encerrada. Status: " + oportunidade.getStatus() + "\n");
        System.out.println("Certificados gerados automaticamente no encerramento:");

// PASSO 7
        List<Certificado> certificados = oportunidadeService.pegarCertificadosDeOportunidade(oportunidade);
        Certificado cert = certificados.getFirst();
        Certificado encontrado = certificadoService.buscar(cert.getCodigoAutenticidade());

        System.out.println("   Certificado encontrado: " + encontrado.getCodigoAutenticidade());
        System.out.println("   Discente     : " + encontrado.getDiscente().getNome());
        System.out.println("   Oportunidade : " + encontrado.getOportunidade().getTitulo());
        System.out.println("   Carga horária: " + encontrado.getCargaHorariaCumprida() + "h");
        System.out.println("   Data emissão : " + encontrado.getDataEmissao());


// PASSO 9 — Submeter solicitação de aproveitamento
        System.out.println("Submetendo solicitação de aproveitamento:");

        // submeter requer: discenteId, descricao, cargaHorariaPleiteada
        Aproveitamento aproveitamento = aprovService.submeter(
                discente.getId(),
                "Participação em workshop de Java fora da universidade",
                20.0
        );
        System.out.println("   Solicitação submetida por: " + discente.getNome());
        System.out.println("   Status: " + aproveitamento.getStatus() + "\n");

// PASSO 10 — Indeferir, reenviar e aprovar o aproveitamento
        System.out.println("Indeferindo solicitação com parecer:");

        // Indeferir requer permissão de COORDENADOR
        aprovService.indeferir(aproveitamento.getId(), coordenador, "Documentação insuficiente");
        System.out.println("   Solicitação indeferida.");
        System.out.println("   Status: " + aproveitamento.getStatus());

        // Discente reenvia a solicitação após correção
        aprovService.reenviar(aproveitamento.getId());
        System.out.println("   Discente reenviou com novos dados e documentação corrigida.");
        System.out.println("   Status: " + aproveitamento.getStatus());

        // Coordenador aprova após análise
        aprovService.aprovar(aproveitamento.getId(), coordenador, aproveitamento.getCargaHorariaPleiteada());
        System.out.println("   Solicitação aprovada pelo avaliador: " + coordenador.getNome());
        System.out.println("   Status: " + aproveitamento.getStatus() + "\n");

// PASSO 11 — Criar grupo e adicionar o discente como DIRETOR
        System.out.println("Criando grupo e adicionando discente como DIRETOR:");

        // criar requer: nome, descricao, email, docenteResponsavelId
        Grupo grupo = grupoService.criar(
                "Diretório Acadêmico de Computação",
                "Representação estudantil do curso de CC",
                "dac@discente.ufma.br",
                docente.getId()
        );
        System.out.println("   Grupo criado: " + grupo.getNome());

        // Cria um DiscenteDiretor com os dados do discente já cadastrado
        // Não há mais o "Cast" de papel.DISCENTE_DIRETOR, isso já está claro em DiscenteDiretor
        DiscenteDiretor diretor = new DiscenteDiretor(
                discente.getNome(),
                discente.getEmail(),
                discente.getSenha(),
                discente.getMatricula(),
                discente.getSemestreAtual(),
                discente.getCurso(),
                grupo,
                "Diretor"
        );

        // Adiciona o diretor como membro do grupo via GrupoService
        grupoService.adicionarMembro(grupo.getId(), diretor.getId(), Cargo.DIRETOR);
        System.out.println("   " + diretor.getNome()
                + " adicionado como DIRETOR."
                + " | Cargo: " + diretor.getCargo()
                + " | Papel: " + diretor.getPapel().getDescricao() + "\n");

// PASSO 12 — Imprimir o resultado final de cada operação
        System.out.println("Resultado Final");
        System.out.println("  Docente cadastrado    : " + docente.getNome());
        System.out.println("  Discente cadastrado   : " + discente.getNome());
        System.out.println("  Coordenador cadastrado: " + coordenador.getNome());
        System.out.println("  Oportunidade          : " + oportunidade.getTitulo()
                + " | Status: " + oportunidade.getStatus());
        System.out.println("  Inscrição             : " + discente.getNome()
                + " → " + oportunidade.getTitulo()
                + " | Status: " + inscricao.getStatus());
        System.out.println("  Certificado emitido   : " + discente.getNome()
                + " | " + encontrado.getCargaHorariaCumprida() + "h"
                + " | Código: " + encontrado.getCodigoAutenticidade());
        System.out.println("  Aproveitamento        : " + discente.getNome()
                + " | Status: " + aproveitamento.getStatus());
        System.out.println("  Grupo/Diretor         : " + diretor.getNome()
                + " | Cargo: " + diretor.getCargo());
    }
}