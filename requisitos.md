# Requisitos — Etapa 1

> Status verificados diretamente no código (branch `master`), não apenas autodeclarados.
> Legenda: **[✅] feito** · **[⚠️] parcial** · **[ ] não feito** · **[ RETIRAR ] descartado (vermelho na planilha)**

## Gestão de Usuários

| Feito | Código | Requisito | Detalhamento |
|-------|--------|-----------|--------------|
| [✅]   | RF001 | O sistema deve permitir que o discente realize o autocadastro na plataforma. | O discente deverá se cadastrar utilizando dados institucionais, como nome completo, matrícula e e-mail institucional. A secretaria ou coordenação poderá apoiar o processo quando necessário. |
| [⚠️]   | RF003 | O sistema deve permitir o cadastro prévio dos perfis de gestores. | `cadastrarDocente` e `cadastrarCoordenador` existem. _Falta: não há perfil Administrador cadastrando previamente, nem envio de e-mail com credenciais de primeiro acesso._ |
| [ ]   | RF004 | O sistema deve permitir o gerenciamento de perfis e permissões. | Perfis predefinidos (`Papel`) existem e há checagem `hasPermissao`. _Falta: atribuição de permissões por módulo/ação e registro (usuário, data, operação) das alterações._ |
| [✅]   | RF006 | O sistema deve permitir o cadastro de grupos de discentes. | `GrupoService.criar` exige vínculo obrigatório de um Docente Responsável (ex.: Diretório Acadêmico, Ligas, Atlética). |
| [ ]   | RF007 | O sistema deve permitir que o coordenador cadastre o Projeto Pedagógico do Curso (PPC). | _Falta: `PPC` existe apenas como entidade; não há serviço para o coordenador cadastrar o PPC e ele não é usado no `Main`._ |
| [⚠️]   | RF008 | O sistema deve manter o histórico de versões do PPC. | _Falta: a entidade `PPC` tem apenas um campo `versao` (int) e `dataInicio`. Não há histórico de versões anteriores, nem registro de autor da alteração. (Anteriormente marcado como feito.)_ |
| [⚠️]   | RF009 | O sistema deve permitir que o docente responsável atribua ou remova cargos dos membros de um grupo estudantil. | O cargo é atribuído ao adicionar o membro (`adicionarMembro`) e `removerMembro` desliga o membro. _Falta: reatribuir/trocar o cargo de um membro já existente (`setCargo` não é exposto por serviço)._ |
| [⚠️]  | RF010 | O sistema deve registrar o histórico de ocupação de cargos em grupos estudantis. | `GrupoMembros` guarda `cargo`, `dataEntrada` e `dataSaida` (preserva o vínculo). _Falta: versionar a troca de cargo ao longo do tempo._ |

## Gestão de Oportunidades

| Feito | Código | Requisito | Detalhamento |
|-------|--------|-----------|--------------|
| [✅]   | RF011 | O sistema deve permitir a criação de oportunidades de extensão. | `criarOportunidade` com título, descrição, modalidade, carga horária, período e vagas, restrito a docente/discente diretor. |
| [✅]   | RF012 | O sistema deve gerenciar o fluxo de estados da oportunidade. | Fluxo completo: Rascunho → Aguardando Aprovação → Aberta → Em Execução → Encerrada / Cancelada. Oportunidades de discente diretor exigem aprovação docente. |
| [✅]   | RF014 | O sistema deve permitir que o discente se inscreva em oportunidades de extensão internas. | `inscrever` valida status ABERTA, vagas disponíveis e inscrição duplicada; registra com status Pendente. |
| [✅]  | RF015 | O sistema deve permitir que o responsável aprove ou rejeite inscrições. | `aprovar`/`rejeitar` com checagem de permissão. _Obs.: checa ADMIN/DOCENTE; não contempla o "líder discente autorizado"._ |
| [✅]  | RF016 | O sistema deve permitir que o discente cancele sua inscrição antes do início da atividade. | `cancelar` verifica a data antes do início. _Obs.: hoje só cancela inscrição com status APROVADA, não PENDENTE._ |
| [ ]  | RF017 | O sistema deve permitir a substituição de participantes. | _Falta: não existe remoção de participante com justificativa + seleção de substituto a partir da lista de interessados._ |
| [⚠️]   | RF019 | O sistema deve permitir o encerramento da oportunidade. | `encerrarOportunidade` marca como Encerrada. _Falta: o encaminhamento da lista de participantes para certificação é feito manualmente no `Main`, não de forma automática. (Anteriormente marcado como feito.)_ |

## Gestão de Solicitações

| Feito | Código | Requisito | Detalhamento |
|------|--------|-----------|--------------|
| [✅] | RF020 | O sistema deve permitir que o discente inicie uma solicitação de aproveitamento. | `submeter` cria a solicitação com status Pendente. _Falta: campo de anexo comprobatório e captura de data de início/fim no formulário._ |
| [✅] | RF021 | O sistema deve permitir que o coordenador visualize e analise as solicitações de aproveitamento. | `listarPendentes` + `aprovar`/`indeferir` (permissão de Coordenador). _Falta: o parecer obrigatório no indeferimento não é aplicado (`setParecer` existe mas não é chamado). (Anteriormente marcado como feito.)_ |
| [ ] | RF022 | O sistema deve impor prazos para decisão do coordenador e para reenvio do discente. | _Falta: nenhuma lógica de prazos (10 dias / 5 dias). A entidade não tem campos de prazo nem checagem de data._ |
| [✅] | RF023 | O sistema deve permitir que o discente reenvie uma solicitação indeferida. | `reenviar` faz INDEFERIDO → PENDENTE. _Obs.: não há checagem da janela de 5 dias._ |
| [⚠️] | RF024 | O sistema deve exibir o acompanhamento de status das solicitações para o discente. | `listarPorDiscente` retorna as solicitações do discente com seus status (Pendente, Aprovado, Indeferido, Cancelado). _Falta: exibição de prazos associados e desacordos com a norma._ |

## Equipe 7 — Requisitos próprios (aba "Equipe 7")

> Requisitos derivados/adicionais do nosso time. Os 8 em vermelho na planilha estão descartados; restam 3 ativos, ainda não implementados.

| Feito | Código | Derivado de | Tipo | Requisito | Detalhamento |
|-------|--------|-------------|------|-----------|--------------|
| [ ] | RF0001 | RF004 | Novo | O sistema deve permitir que o Administrador desative ou anonimize contas de discentes e docentes que perderam o vínculo com a instituição. | Deve preservar o histórico para auditoria, mas impedir que a conta desativada realize novas ações (inscrever-se, ser vinculada a projetos). _Base existente: flag `ativo` em `Usuario` (`isAtivo`/`setAtivo`). Falta: método de desativação/anonimização, papel Admin executando, e bloqueio de contas inativas nas ações._ |
| [ ] | RF0002 | RF006 | Novo | O sistema deve permitir que um discente diretor solicite o cadastro de um novo grupo estudantil. | O formulário deve exigir Docente Responsável (RF006), nome, descrição e objetivos, encaminhando para aprovação do Administrador/Coordenador. _Falta: `GrupoService.criar` cria o grupo direto como `ATIVO`; `StatusGrupo` só tem ATIVO/INATIVO. Não há estado "aguardando aprovação" nem fluxo de solicitação iniciado pelo diretor._ |
| [ ] | RF0009 | RF007/RF008 | Sugestão do Cliente | O sistema deve permitir controle individual de PPC, UCEs, carga horária e modularidade. | Cadastrar UCEs (Unidades Curriculares de Extensão) por PPC, vincular cada aluno ao seu PPC e suportar múltiplos PPCs (ex.: 2020 e 2025) ao mesmo tempo. _Falta: não existe entidade UCE; o `Discente` se liga a `Curso`, não a um PPC; `PPC` é só entidade básica sem vínculo com aluno._ |

## Resumo

**Requisitos consolidados ativos (20):**
- **Feitos (9):** RF001, RF006, RF011, RF012, RF014, RF015, RF016, RF020, RF023
- **Parciais (6):** RF003, RF009, RF010, RF019, RF021, RF024
- **Não feitos (5):** RF004, RF007, RF008, RF017, RF022

**Requisitos próprios da Equipe 7 ativos (3):**
- **Não feitos (3):** RF0001, RF0002, RF0009

**Descartados:** RF002, RF005, RF013, RF018, RF025–RF047 (consolidados) e RF0003-A, RF0003-B, RF0004, RF0005, RF0006, RF0007, RF0008, RF0010 (Equipe 7).
