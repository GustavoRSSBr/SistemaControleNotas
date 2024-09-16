package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.enums;

public enum MensagemErroApplication {
    EMAIL_JA_CADASTRADO("E-mail já cadastrado."),

    DATA_INVALIDA("Data inválida: "),
    EMAIL_USUARIO_INVALIDO("Credenciais inválidas");

    private final String mensagem;

    MensagemErroApplication(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}

