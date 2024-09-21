package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.MensagemErro;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.exceptions.NegocioException;

public class Entrega {
    private Integer idAluno;
    private Integer idAvaliacao;
    private String conteudo;

    public Entrega(Integer idAluno, Integer idAvaliacao, String conteudo) {
        if (idAluno == null || idAvaliacao == null) {
            throw new NegocioException(MensagemErro.ID_INVALIDO_AVALIACAO.getMensagem());
        }
        if (conteudo == null || conteudo.trim().isEmpty()) {
            throw new NegocioException(MensagemErro.CONTEUDO_VAZIO.getMensagem());
        }
        this.idAluno = idAluno;
        this.idAvaliacao = idAvaliacao;
        this.conteudo = conteudo.trim();
    }

    public Integer getIdAluno() {
        return idAluno;
    }

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public String getConteudo() {
        return conteudo;
    }
}

