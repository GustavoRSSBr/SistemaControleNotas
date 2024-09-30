package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega.EntregaController;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.AlunoRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.LoginRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.ProfessorRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.dto.StandardResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.TokenResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.MensagemSucesso;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.usuario.IUsuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scn")
public class UsuarioController implements IUsuarioController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntregaController.class);
    @Autowired
    private IUsuario usuarioService;

    @Override
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        LOGGER.info("Início do método login com request: {}", loginRequestDTO.getEmail());
        long startTime = System.currentTimeMillis();

        TokenResponseDTO tokenResponseDTO = new TokenResponseDTO(usuarioService.autenticar(loginRequestDTO));

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        LOGGER.info("Tempo decorrido: " + elapsedTime + " milissegundos");

        return ResponseEntity.ok(
                StandardResponseDTO.builder()
                        .messagem(MensagemSucesso.LOGIN.getMensagem())
                        .dados(tokenResponseDTO)
                        .build()
        );
    }

    @Override
    @PostMapping("/admin/cadastrar-professor")
    public ResponseEntity<?> cadastrarProfessor(@RequestBody ProfessorRequestDTO professorRequestDTO) {
        LOGGER.info("Início do método cadastrarProfessor com request de dados do professor");
        long startTime = System.currentTimeMillis();

        usuarioService.cadastrarProfessor(professorRequestDTO);

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        LOGGER.info("Tempo decorrido: " + elapsedTime + " milissegundos");

        return ResponseEntity.ok(
                StandardResponseDTO.builder()
                        .messagem(MensagemSucesso.CADASTRAR_PROFESSOR.getMensagem())
                        .build()
        );
    }

    @Override
    @PostMapping("/alunos/cadastrar-aluno")
    public ResponseEntity<?> cadastrarAluno(@RequestBody AlunoRequestDTO alunoRequestDTO) {
        LOGGER.info("Início do método cadastrarAluno com request de dados do aluno");
        long startTime = System.currentTimeMillis();

        usuarioService.cadastrarAluno(alunoRequestDTO);

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        LOGGER.info("Tempo decorrido: " + elapsedTime + " milissegundos");

        return ResponseEntity.ok(
                StandardResponseDTO.builder()
                        .messagem(MensagemSucesso.CADASTRAR_ALUNO.getMensagem())
                        .build()
        );
    }
}
