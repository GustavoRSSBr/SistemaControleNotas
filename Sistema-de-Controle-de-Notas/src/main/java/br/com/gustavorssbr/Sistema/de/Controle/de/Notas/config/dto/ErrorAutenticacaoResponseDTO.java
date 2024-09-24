package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorAutenticacaoResponseDTO  {
    private String mensagem;
    private String path;
    private Integer code;
}
