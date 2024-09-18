package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoDTO {
    private String titulo;
    private String descricao;
    private String dataEntrega; // Receber a data como String

}

