package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums;

public enum MensagemErroDomain {
    NOME_USUARIO_VAZIO("Nome não pode ser vazio."),
    EMAIL_USUARIO_INVALIDO("E-mail inválido."),
    SENHA_USUARIO_CURTA("A senha deve ter pelo menos 6 caracteres."),
    TITULO_NULO("O título da avaliação não pode ser nulo ou vazio."),
    DATA_ANTERIOR_ATUAL("A data de entrega não pode ser menor que a data atual."),
    TIPO_USUARIO_OBRIGATORIO("Tipo de usuário é obrigatório.");

    private final String mensagem;

    MensagemErroDomain(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}

