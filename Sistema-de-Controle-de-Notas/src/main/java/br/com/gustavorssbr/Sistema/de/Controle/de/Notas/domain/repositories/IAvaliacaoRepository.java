package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.repositories;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Avaliacao;

import java.time.LocalDateTime;

public interface IAvaliacaoRepository {
    Integer criarAvaliacao(Avaliacao avaliacao);
}
