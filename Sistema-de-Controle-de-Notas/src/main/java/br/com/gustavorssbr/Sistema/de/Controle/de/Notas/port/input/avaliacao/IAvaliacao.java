package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.avaliacao;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto.AvaliacaoDTO;


public interface IAvaliacao {
    Integer criarAvaliacao(AvaliacaoDTO avaliacaoDTO, String token);
}
