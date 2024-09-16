package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.services;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.dtos.request.AvaliacaoDTO;

public interface IAvaliacaoService {
    Integer criarAvaliacao(AvaliacaoDTO avaliacaoDTO, String token);
}
