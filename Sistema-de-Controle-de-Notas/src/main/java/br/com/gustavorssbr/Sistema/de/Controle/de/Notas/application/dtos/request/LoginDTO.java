package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LoginDTO {
    private String email;
    private String senha;
}
