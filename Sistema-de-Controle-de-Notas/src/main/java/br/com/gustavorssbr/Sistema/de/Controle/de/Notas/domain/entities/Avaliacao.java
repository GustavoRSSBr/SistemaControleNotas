package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.MensagemErro;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.exceptions.NegocioException;

import java.time.LocalDateTime;

public class Avaliacao {
    private String titulo;
    private String descricao;
    private LocalDateTime dataEntrega;
    private Integer professorId;

    public Avaliacao(String titulo, String descricao, LocalDateTime dataEntrega, Integer professorId) {
        if (dataEntrega.isBefore(LocalDateTime.now())) {
            throw new NegocioException(MensagemErro.DATA_ANTERIOR_ATUAL.getMensagem());
        }

        if (titulo == null || titulo.trim().isEmpty()) {
            throw new NegocioException(MensagemErro.TITULO_NULO.getMensagem());
        }

        this.titulo = titulo.trim();
        this.descricao = descricao.trim();
        this.dataEntrega = dataEntrega;
        this.professorId = professorId;
    }
    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }

    public Integer getProfessorId() {
        return professorId;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataEntrega(LocalDateTime dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public void setProfessorId(Integer professorId) {
        this.professorId = professorId;
    }
}
