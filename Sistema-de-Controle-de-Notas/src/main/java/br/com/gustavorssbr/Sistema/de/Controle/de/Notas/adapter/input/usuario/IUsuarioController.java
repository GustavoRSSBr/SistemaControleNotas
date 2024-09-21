package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.AlunoRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.LoginRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.ProfessorRequestDTO;
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
    ResponseEntity<?> login(LoginRequestDTO loginRequestDTO);

    @Operation(summary = "Cadastro de professor", description = "Cadastra um novo professor no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Professor cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para cadastro do professor"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> cadastrarProfessor(ProfessorRequestDTO professorRequestDTO);

    @Operation(summary = "Cadastro de aluno", description = "Cadastra um novo aluno no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para cadastro do aluno"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    ResponseEntity<?> cadastrarAluno(AlunoRequestDTO alunoRequestDTO);
}
