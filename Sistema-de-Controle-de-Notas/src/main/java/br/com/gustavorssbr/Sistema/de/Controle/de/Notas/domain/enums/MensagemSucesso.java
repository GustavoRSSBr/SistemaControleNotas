package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums;

public enum MensagemSucesso {
    BUSCAR_NOTA("Nota encontrada com sucesso!"),
    LISTAR_AVALIACAO("Avaliações encontradas com sucesso!"),
    CRIAR_NOTA("Nota lançada com sucesso!"),
    CRIAR_AVALIACAO("Avaliação criada com sucesso!"),
    CRIAR_ENTREGA("Entrega feita com sucesso!"),
    LISTAR_ENTREGA("Entregas encontradas com sucesso!"),
    LOGIN("Login realizado com sucesso!"),
    CADASTRAR_PROFESSOR("Professor cadastrado com sucesso!"),
    CADASTRAR_ALUNO("Aluno cadastrado com sucesso!");



    private final String mensagem;

    MensagemSucesso(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
