package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.entrega;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega.dto.EntregaRequestDTO;


public interface IEntrega {
    Integer criarEntrega(EntregaRequestDTO entregaRequestDTO, String token);
}
