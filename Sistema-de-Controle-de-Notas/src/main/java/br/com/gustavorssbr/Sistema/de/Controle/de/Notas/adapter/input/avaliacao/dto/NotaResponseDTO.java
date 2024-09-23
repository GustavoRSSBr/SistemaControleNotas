package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotaResponseDTO {
    private Integer idNota;
    private Integer entregaId;
    private Double nota;
    private String feedback;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAtribuicao;
}

