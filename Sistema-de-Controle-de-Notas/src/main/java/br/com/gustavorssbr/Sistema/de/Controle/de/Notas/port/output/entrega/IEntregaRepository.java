package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.output.entrega;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Entrega;

public interface IEntregaRepository {
    Integer criarEntrega(Entrega entrega);
    boolean existeAvaliacao(int idAvaliacao);
    boolean existeEntregaParaAvaliacao(int idAvaliacao, int idAluno);
}

