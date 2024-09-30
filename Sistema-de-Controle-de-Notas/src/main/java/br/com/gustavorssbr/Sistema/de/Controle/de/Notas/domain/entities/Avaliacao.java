package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Avaliacao {
    private Integer idAvaliacao;
    private String titulo;
    private String descricao;
    private LocalDateTime dataEntrega;
    private LocalDateTime dataCriacao;
    private Integer professorId;

    public Integer getIdAvaliacao() {return idAvaliacao;}

    public void setIdAvaliacao(Integer idAvaliacao) {this.idAvaliacao = idAvaliacao;}

    public LocalDateTime getDataCriacao() {return dataCriacao;}

    public void setDataCriacao(LocalDateTime dataCriacao) {this.dataCriacao = dataCriacao;}

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

    @Override
    public String toString() {
        return "Avaliacao{" +
                "idAvaliacao=" + idAvaliacao +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataEntrega=" + dataEntrega +
                ", dataCriacao=" + dataCriacao +
                ", professorId=" + professorId +
                '}';
    }
}
