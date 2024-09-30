package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.output.avaliacao;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto.AtualizaNotaRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto.AvaliacaoResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto.NotaResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Avaliacao;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Nota;

import java.util.List;


public interface IAvaliacaoRepository {
    Integer criarAvaliacao(Avaliacao avaliacao);

    boolean existeEntrega(int idEntrega);

    boolean validarRelacaoEntregaComProfessor(int idProfessor, Integer idEntrega);

    Integer lancarNota(Nota nota);

    boolean existeNotaNaEntrega(int idEntrega);

    List<AvaliacaoResponseDTO> listarAvaliacoes();

    boolean verificarEntregaAssociada(int idAluno, int idEntrega);

    NotaResponseDTO retornarInformacoesNotaPorEntrega(int entregaId);

    void atualizarNota(AtualizaNotaRequestDTO novaNota);

    boolean existeNota(int idNota);

    boolean existeRelacaoProfessorNota(int idProfessor, int idNota);
}
