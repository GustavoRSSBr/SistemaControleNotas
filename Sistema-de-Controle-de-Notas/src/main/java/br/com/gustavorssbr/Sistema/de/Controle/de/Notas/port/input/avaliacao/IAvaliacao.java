package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.avaliacao;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto.*;

import java.util.List;


public interface IAvaliacao {
    Integer criarAvaliacao(AvaliacaoRequestDTO avaliacaoRequestDTO, String token);

    Integer lancarNota(NotaRequestDTO nota, String token);

    List<AvaliacaoResponseDTO> listarAvaliacoes();

    NotaResponseDTO buscarNota(String token, int idEntrega);

    void atualizarNota(String token, AtualizaNotaRequestDTO novaNota);
}
