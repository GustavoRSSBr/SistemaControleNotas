package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EntregaRequestDTO {
    private Integer idAvaliacao;
    private String conteudo;
}
