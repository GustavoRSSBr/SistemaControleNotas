package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.output.avaliacao;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Avaliacao;


public interface IAvaliacaoRepository {
    Integer criarAvaliacao(Avaliacao avaliacao);
}
