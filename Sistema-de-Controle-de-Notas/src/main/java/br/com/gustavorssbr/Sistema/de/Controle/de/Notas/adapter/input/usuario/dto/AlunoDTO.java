package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDTO {
    private String email;
    private String nome;
    private String senha;

}
