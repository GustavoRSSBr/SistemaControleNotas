package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.AlunoRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.LoginRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.ProfessorRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.dto.StandardResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.TokenResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.usuario.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scn")
public class UsuarioController implements IUsuarioController {
    @Autowired
    private IUsuario usuarioService;

    @Override
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        TokenResponseDTO tokenResponseDTO = new TokenResponseDTO(usuarioService.autenticar(loginRequestDTO));
        return ResponseEntity.ok(
                StandardResponseDTO.builder()
                        .messagem("Login realizado com sucesso!")
                        .dados(tokenResponseDTO)
                        .build()
        );
    }

    @Override
    @PostMapping("/professores/cadastrar-professor")
    public ResponseEntity<?> cadastrarProfessor(@RequestBody ProfessorRequestDTO professorRequestDTO) {
        usuarioService.cadastrarProfessor(professorRequestDTO);
        return ResponseEntity.ok(
                StandardResponseDTO.builder()
                        .messagem("Professor cadastrado com sucesso!")
                        .build()
        );
    }

    @Override
    @PostMapping("/alunos/cadastrar-aluno")
    public ResponseEntity<?> cadastrarAluno(@RequestBody AlunoRequestDTO alunoRequestDTO) {
        usuarioService.cadastrarAluno(alunoRequestDTO);
        return ResponseEntity.ok(
                StandardResponseDTO.builder()
                        .messagem("Aluno cadastrado com sucesso!")
                        .build()
        );
    }
}
