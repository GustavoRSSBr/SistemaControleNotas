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
    ENTREGA_JA_EXISTE("Já foi feita uma entrega para essa avaliação");

    private final String mensagem;

    MensagemErro(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}

