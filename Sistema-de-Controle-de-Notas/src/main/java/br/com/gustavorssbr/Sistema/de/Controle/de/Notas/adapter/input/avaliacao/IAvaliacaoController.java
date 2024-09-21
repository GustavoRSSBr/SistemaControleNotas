package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto.AvaliacaoRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto.NotaRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface IAvaliacaoController {

    @Operation(summary = "Criar Avaliação", description = "Cria uma nova avaliação para o professor autenticado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Avaliação criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para a criação da avaliação"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> criarAvaliacao(AvaliacaoRequestDTO avaliacaoRequestDTO, String token);

    @Operation(summary = "Lançar Nota", description = "O Professor autenticado consegue lançar uma nota a uma entrega referente a uma avaliação dele")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nota enviada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para a criação da nota"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> criarNota(NotaRequestDTO notaRequestDTO, String token);
}
