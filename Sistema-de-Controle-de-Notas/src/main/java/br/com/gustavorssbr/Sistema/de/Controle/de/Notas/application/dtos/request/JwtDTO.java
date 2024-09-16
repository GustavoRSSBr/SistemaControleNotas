package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.dtos.request;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.TipoUsuario;
import lombok.Data;

@Data
public class JwtDTO {
    private int id;
    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;
}
