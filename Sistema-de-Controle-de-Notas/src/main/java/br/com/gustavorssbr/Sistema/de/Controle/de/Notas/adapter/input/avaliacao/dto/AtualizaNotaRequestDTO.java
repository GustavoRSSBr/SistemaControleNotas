package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtualizaNotaRequestDTO {
    Integer idNota;
    Double valorNota;
    String feedback;
}
