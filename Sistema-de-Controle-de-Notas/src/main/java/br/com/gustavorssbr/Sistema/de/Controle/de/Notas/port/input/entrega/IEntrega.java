package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.entrega;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega.dto.EntregaRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega.dto.EntregaResponseDTO;

import java.util.List;


public interface IEntrega {
    Integer criarEntrega(EntregaRequestDTO entregaRequestDTO, String token);

    List<EntregaResponseDTO> listarEntregasDaAvaliacao(String token, int idAvaliacao);
}
