# Requisitos — Etapa 1 (trabalho-1-entrega)

## Gestão de Usuários

| Feito | Código | Requisito | Detalhamento |
|-------|--------|-----------|--------------|
| [ ] | RF001 | O sistema deve permitir que o discente realize o autocadastro na plataforma. | O discente deverá se cadastrar utilizando dados institucionais, como nome completo, matrícula e e-mail institucional. A secretaria ou coordenação poderá apoiar o processo quando necessário. |
| [ ] | RF003 | O sistema deve permitir que o administrador cadastre previamente os perfis de gestores. | O administrador poderá cadastrar Docente, Coordenador, Comissão e Secretária, informando nome, e-mail institucional e matrícula/SIAPE. |
| [ ] | RF004 | O sistema deve permitir o gerenciamento de perfis e permissões de acesso. | O sistema deve possuir perfis predefinidos (Discente, Docente, Coordenador, Comissão/Secretaria, Administrador) e permitir a atribuição de permissões por módulo e ação. |
| [ ] | RF006 | O sistema deve permitir o cadastro de grupos de discentes. | A coordenação ou o administrador poderão cadastrar grupos (ex.: Diretório Acadêmico, Ligas, Atlética), vinculando obrigatoriamente um Docente Responsável. |
| [ ] | RF007 | O sistema deve permitir que o coordenador cadastre o Projeto Pedagógico do Curso (PPC). | O coordenador deverá cadastrar o PPC vigente, incluindo a carga horária mínima de extensão exigida. |
| [ ] | RF008 | O sistema deve manter o histórico de versões do PPC. | Sempre que o PPC for atualizado, o sistema deverá preservar as versões anteriores, registrando autor da alteração e data de vigência. |
| [ ] | RF009 | O sistema deve permitir que o docente responsável atribua ou remova cargos dos membros de um grupo estudantil. | Papéis como Diretor, Vice, Tesoureiro e Membro poderão ser atribuídos ou removidos pelo Docente Responsável. |
| [ ] | RF010 | O sistema deve registrar o histórico de ocupação de cargos em grupos estudantis. | O sistema deve guardar quem ocupou cada cargo e o período correspondente. |

## Gestão de Oportunidades

| Feito | Código | Requisito | Detalhamento |
|-------|--------|-----------|--------------|
| [ ] | RF011 | O sistema deve permitir a criação de oportunidades de extensão. | Docentes, coordenadores ou discentes líderes poderão cadastrar oportunidades com título, descrição, modalidade, carga horária, período e vagas. |
| [ ] | RF012 | O sistema deve gerenciar o fluxo de estados da oportunidade. | Estados: Rascunho → Aguardando Aprovação → Aberta → Em Execução → Encerrada / Cancelada. Oportunidades de discentes exigem validação docente. |
| [ ] | RF014 | O sistema deve permitir que o discente se inscreva em oportunidades de extensão internas. | Durante o período de inscrição, o discente poderá se inscrever; o sistema registrará a inscrição com status Pendente. |
| [ ] | RF015 | O sistema deve permitir que o responsável aprove ou rejeite inscrições. | O responsável (docente ou líder discente autorizado) poderá aprovar ou rejeitar inscrições conforme critérios definidos na ação. |
| [ ] | RF016 | O sistema deve permitir que o discente cancele sua inscrição antes do início da atividade. | O discente poderá cancelar a participação dentro do prazo definido pela oportunidade. |
| [ ] | RF017 | O sistema deve permitir a substituição de participantes. | O responsável poderá remover um participante com justificativa e selecionar outro da lista de interessados. |
| [ ] | RF019 | O sistema deve permitir o encerramento da oportunidade. | Ao término, a oportunidade é marcada como Encerrada e a lista de participantes é encaminhada para certificação. |

## Gestão de Solicitações

| Feito | Código | Requisito | Detalhamento |
|-------|--------|-----------|--------------|
| [ ] | RF020 | O sistema deve permitir que o discente inicie uma solicitação de aproveitamento de horas externas. | O formulário deve exigir descrição da atividade, carga horária pleiteada, data de início e fim. Status inicial: Pendente. |
| [ ] | RF021 | O sistema deve permitir que o coordenador visualize e analise as solicitações de aproveitamento. | O coordenador poderá consultar a fila de pendências e emitir decisão com parecer obrigatório em caso de indeferimento. |
| [ ] | RF023 | O sistema deve permitir que o discente reenvie uma solicitação indeferida. | O discente poderá editar os campos e reenviar a solicitação dentro do prazo de 5 dias após o indeferimento. |

## Certificação e Validação

| Feito | Código | Requisito | Detalhamento |
|-------|--------|-----------|--------------|
| [ ] | RF025 | O sistema deve gerar certificados para participantes de atividades internas concluídas. | O certificado deve conter nome do participante, dados da atividade, período de cumprimento e carga horária cumprida. |
| [ ] | RF026 | O sistema deve incluir um código de autenticidade único nos certificados emitidos. | Cada certificado deve possuir um identificador único (ex.: CERT-{id}) para verificação de autenticidade. |
