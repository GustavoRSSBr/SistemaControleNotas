package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioResponseDTO {
    private Integer idUsuario;
    private String nome;
    private String email;
    private String tipoUsuario;
}
