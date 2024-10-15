package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.BaseTest;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.LoginRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.TokenResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.dto.StandardResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.MensagemSucesso;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.usuario.IUsuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest extends BaseTest {

    @Mock
    private IUsuario usuarioCommand;

    @InjectMocks
    private UsuarioController usuarioController;

    @Test
    void loginFluxo200() {
        when(usuarioCommand.autenticar(LoginRequestDTO.builder()
                        .email("adm@adm.com")
                        .senha("SenhaSegura@123")
                .build())).thenReturn("token");

        ResponseEntity<?> response = usuarioController.login(criarLoginRequestDTO());

        var body = (StandardResponseDTO) response.getBody();

        TokenResponseDTO token = (TokenResponseDTO) body.getDados();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MensagemSucesso.LOGIN.getMensagem(), body.getMessagem());
        assertEquals("token", token.getToken());
    }

    @Test
    void cadastrarProfessorFluxo200() {
        ResponseEntity<?> response = usuarioController.cadastrarProfessor(criarProfessorRequestDTO());

        var body = (StandardResponseDTO) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MensagemSucesso.CADASTRAR_PROFESSOR.getMensagem(), body.getMessagem());
    }

    @Test
    void cadastrarAlunoFluxo200() {
        ResponseEntity<?> response = usuarioController.cadastrarAluno(super.cadatrarAlunoRequestDTO());

        var body = (StandardResponseDTO) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MensagemSucesso.CADASTRAR_ALUNO.getMensagem(), body.getMessagem());

    }
}