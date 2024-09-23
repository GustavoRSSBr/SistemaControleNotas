package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums;

public enum MensagemErro {
    EMAIL_JA_CADASTRADO("E-mail já cadastrado."),
    DATA_INVALIDA("Data inválida: "),
    EMAIL_USUARIO_INVALIDO("Credenciais inválidas"),
    NOME_USUARIO_VAZIO("Nome não pode ser vazio."),
    SENHA_USUARIO_CURTA("A senha deve ter pelo menos 6 caracteres."),
    TITULO_NULO("O título da avaliação não pode ser nulo ou vazio."),
    DATA_ANTERIOR_ATUAL("A data de entrega não pode ser menor que a data atual."),
    TIPO_USUARIO_OBRIGATORIO("Tipo de usuário é obrigatório."),
    ID_INVALIDO_AVALIACAO("Avaliação não existe"),
    CONTEUDO_VAZIO("Conteudo vazio"),
    AVALIACAO_NAO_EXISTE("Avaliação não existe"),
    ENTREGA_NAO_EXISTE("Entrega não existe"),
    ENTREGA_JA_EXISTE("Já foi feita uma entrega para essa avaliação"),
    NEGOCIO_EXCEPTION("Erro de negócio: "),
    APLICACAO_EXCEPTION("Erro de aplicação: "),
    GENERIC_ERROR("Ocorreu um erro inesperado. Por favor, tente novamente mais tarde."),
    DESC_BAD_REQUEST_HEADERS("Efetue o login corretamente"),
    DESC_ROLE_SEM_PERMISSAO("Sem permissão para acessar a rota"),
    DESC_TOKEN_INVALIDO("Token inválido"),
    ENTREGA_NAO_PERTENCE_AO_PROFESSOR("Essa entrega não pertence a uma avaliação do professor logado"),
    NOTA_INVALIDA("Nota inválida, a nota deve estar entre 0 e 10"),
    JA_EXISTE_NOTA("Já existe uma nota para essa entrega"),
    TEMPO_ENTREGA_EXPIRADO("O tempo para a entrega expirou."),
    SEM_AVALIACOES("Nenhuma avaliação encontrada"),
    NAO_EXISTE_NOTA("Essa entrega ainda não contém uma nota"),
    ALUNO_SEM_PERMISSAO_NOTA("O aluno logado não pode ver as notas de outros alunos, exceto a dele."),
    PROFESSOR_SEM_PERMISSAO_ENTREGA("O professor só poderá ver as entregas de suas avaliações"),
    LISTA_ENTREGA_VAZIA("Ainda não há entregas para essa avaliação");


    private final String mensagem;

    MensagemErro(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}

