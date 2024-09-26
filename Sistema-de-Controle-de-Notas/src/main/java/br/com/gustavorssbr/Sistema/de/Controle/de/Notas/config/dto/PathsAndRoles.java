package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.dto;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PathsAndRoles {

    private String path;
    private List<TipoUsuario> roles;
}
