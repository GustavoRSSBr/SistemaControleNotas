package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.infrastructure.enums;

public enum ErrorMessages {
    NEGOCIO_EXCEPTION("Erro de negócio: "),
    APPLICATION_EXCEPTION("Erro de aplicação: "),
    GENERIC_ERROR("Ocorreu um erro inesperado. Por favor, tente novamente mais tarde."),
    DESC_BAD_REQUEST_HEADERS("Efetue o login corretamente"),
    DESC_ROLE_SEM_PERMISSAO("Sem permissão para acessar a rota"),
    DESC_TOKEN_INVALIDO("Token Invalido");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
