package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.entrega;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega.dto.EntregaDTO;


public interface IEntrega {
    Integer criarEntrega(EntregaDTO entregaDTO, String token);
}