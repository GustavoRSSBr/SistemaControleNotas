package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.MensagemErro;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.exceptions.NegocioException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Entrega {
    private Integer idAluno;
    private Integer idAvaliacao;
    private String conteudo;

    public Integer getIdAluno() {
        return idAluno;
    }

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public String getConteudo() {
        return conteudo;
    }

    @Override
    public String toString() {
        return "Entrega{" +
                "idAluno=" + idAluno +
                ", idAvaliacao=" + idAvaliacao +
                ", conteudo='" + conteudo + '\'' +
                '}';
    }
}

