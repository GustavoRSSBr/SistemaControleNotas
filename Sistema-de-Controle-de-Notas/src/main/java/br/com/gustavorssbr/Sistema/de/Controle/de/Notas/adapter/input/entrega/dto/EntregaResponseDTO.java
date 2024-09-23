package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega.dto;

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
public class EntregaResponseDTO {
    private Integer idEntrega;
    private String nomeAluno;
    private String emailAluno;
    private Integer avaliacaoId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataSubmissao;
    private String conteudo;
}

