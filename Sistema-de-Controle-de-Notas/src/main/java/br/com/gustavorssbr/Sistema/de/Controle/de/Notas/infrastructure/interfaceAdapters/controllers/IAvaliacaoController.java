package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.infrastructure.interfaceAdapters.controllers;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.dtos.request.AvaliacaoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public interface IAvaliacaoController {

    @Operation(summary = "Criar Avaliação", description = "Cria uma nova avaliação para o professor autenticado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Avaliação criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para a criação da avaliação"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<Integer> criarAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDTO, @RequestHeader("Authorization") String token);
}
