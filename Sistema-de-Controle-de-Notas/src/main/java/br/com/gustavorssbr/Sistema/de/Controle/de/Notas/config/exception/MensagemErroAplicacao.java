package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.exception;

public enum MensagemErroAplicacao {
    NEGOCIO_EXCEPTION("Erro de negócio: "),
    APLICACAO_EXCEPTION("Erro de aplicação: "),
    GENERIC_ERROR("Ocorreu um erro inesperado. Por favor, tente novamente mais tarde."),
    DESC_BAD_REQUEST_HEADERS("Efetue o login corretamente"),
    DESC_ROLE_SEM_PERMISSAO("Sem permissão para acessar a rota"),
    DESC_TOKEN_INVALIDO("Token inválido");


    private final String mensagem;

    MensagemErroAplicacao(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}


