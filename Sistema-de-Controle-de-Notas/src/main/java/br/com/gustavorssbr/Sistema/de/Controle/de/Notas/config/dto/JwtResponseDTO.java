package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.dto;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.TipoUsuario;
import lombok.Data;

@Data
public class JwtResponseDTO {
    private int id;
    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;
}
