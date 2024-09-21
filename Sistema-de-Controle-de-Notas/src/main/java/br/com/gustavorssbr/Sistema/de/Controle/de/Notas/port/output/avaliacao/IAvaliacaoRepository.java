package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.output.avaliacao;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Avaliacao;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Nota;

import java.time.LocalDateTime;


public interface IAvaliacaoRepository {
    Integer criarAvaliacao(Avaliacao avaliacao);

    boolean existeEntrega(int idEntrega);

    boolean validarRelacaoEntregaComProfessor(int idProfessor, Integer idEntrega);

    Integer lancarNota(Nota nota);

    boolean existeNotaNaEntrega(int idEntrega);

}
