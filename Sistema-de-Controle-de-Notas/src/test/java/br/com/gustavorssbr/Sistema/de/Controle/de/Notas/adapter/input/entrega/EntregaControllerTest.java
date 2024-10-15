package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.BaseTest;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega.dto.EntregaResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.dto.IdResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.dto.StandardResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.MensagemSucesso;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.entrega.IEntrega;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EntregaControllerTest extends BaseTest {

    @Mock
    private IEntrega entregaCommand;

    @InjectMocks
    private EntregaController entregaController;


    @Test
    void criarEntregaFluxo200() {
        when(entregaCommand.criarEntrega(any(), anyString())).thenReturn(1);

        ResponseEntity<?> response = entregaController.criarEntrega(criarEntregaRequestDTO(), "token");
        var body = (StandardResponseDTO) response.getBody();
        IdResponseDTO id = (IdResponseDTO) body.getDados();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MensagemSucesso.CRIAR_ENTREGA.getMensagem(), body.getMessagem());
        assertEquals(1, id.getId());
    }

    @Test
    void listarEntregasFluxo200() {
        List<EntregaResponseDTO> entregas = new ArrayList<>();
        entregas.add(EntregaResponseDTO.builder()
                .idEntrega(1)
                .avaliacaoId(1)
                .dataSubmissao(LocalDateTime.now())
                .emailAluno("gustavo@gustavo.com")
                .nomeAluno("Gustavo")
                .conteudo("Conteudo Teste")
                .build());

        when(entregaCommand.listarEntregasDaAvaliacao("token", 1)).thenReturn(entregas);

        ResponseEntity<?> response = entregaController.listarEntregas("token", 1);

        var body = (StandardResponseDTO) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MensagemSucesso.LISTAR_ENTREGA.getMensagem(), body.getMessagem());
        assertEquals(entregas, body.getDados());
    }


}