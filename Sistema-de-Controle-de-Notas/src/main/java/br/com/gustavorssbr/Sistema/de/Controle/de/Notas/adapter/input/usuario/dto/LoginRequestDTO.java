package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LoginRequestDTO {
    private String email;
    private String senha;
}
