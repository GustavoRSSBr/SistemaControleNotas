package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.MensagemErro;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.exceptions.NegocioException;

import java.time.LocalDateTime;

public class Nota {
    private Integer idNota;
    private Integer idEntrega;
    private Double nota;
    private String feedback;

    public Nota(Integer idEntrega, Double nota, String feedback) {
        if(nota == null || nota.isNaN() || nota > 10 || nota < 0){
            throw new NegocioException(MensagemErro.NOTA_INVALIDA.getMensagem());
        }

        this.idEntrega = idEntrega;
        this.nota = nota;
        this.feedback = feedback;
    }

    public Integer getIdNota() {
        return idNota;
    }

    public void setIdNota(Integer idNota) {
        this.idNota = idNota;
    }

    public Integer getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(Integer idEntrega) {
        this.idEntrega = idEntrega;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
