package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotaRequestDTO {
    Integer idEntrega;
    Double valorNota;
    String feedback;
}
