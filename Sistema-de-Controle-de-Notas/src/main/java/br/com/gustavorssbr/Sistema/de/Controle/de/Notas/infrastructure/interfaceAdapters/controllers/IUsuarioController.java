package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.infrastructure.interfaceAdapters.controllers;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.dtos.request.AlunoDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.dtos.request.LoginDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.dtos.request.ProfessorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface IUsuarioController {

    @Operation(summary = "Login de usuário", description = "Autentica um usuário e retorna um token JWT.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login efetuado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de login inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<String> login(LoginDTO loginDTO);

    @Operation(summary = "Cadastro de professor", description = "Cadastra um novo professor no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Professor cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para cadastro do professor"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<Integer> cadastrarProfessor(ProfessorDTO professorDTO);

    @Operation(summary = "Cadastro de aluno", description = "Cadastra um novo aluno no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para cadastro do aluno"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<Integer> cadastrarAluno(AlunoDTO alunoDTO);
}
