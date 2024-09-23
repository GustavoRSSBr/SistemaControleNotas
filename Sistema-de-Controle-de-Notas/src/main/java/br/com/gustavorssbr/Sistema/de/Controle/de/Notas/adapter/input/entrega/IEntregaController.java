package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega.dto.EntregaRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface IEntregaController {

    @Operation(summary = "Criar uma nova entrega", description = "Cria uma nova entrega de avaliação para um aluno.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entrega criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para criação da entrega"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> criarEntrega(EntregaRequestDTO entregaRequestDTO, String token);

    @Operation(summary = "Listar entregas da avaliação", description = "O professor poderá listar todas as entregas de sua avaliação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entregas encontradas"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou entregas vazias"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> listarEntregas(String token, int idAvaliacao);
}

