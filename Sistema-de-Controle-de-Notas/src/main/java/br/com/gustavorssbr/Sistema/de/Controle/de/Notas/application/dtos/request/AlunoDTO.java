package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.dtos.request;

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
