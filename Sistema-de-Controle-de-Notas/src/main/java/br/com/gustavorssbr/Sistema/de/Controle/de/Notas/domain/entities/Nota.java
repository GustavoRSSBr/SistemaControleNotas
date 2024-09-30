package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Nota {
    private Integer idNota;
    private Integer idEntrega;
    private Double nota;
    private String feedback;

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

    @Override
    public String toString() {
        return "Nota{" +
                "idNota=" + idNota +
                ", idEntrega=" + idEntrega +
                ", nota=" + nota +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
