package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto.AvaliacaoResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto.NotaResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.dto.IdResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.dto.StandardResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.MensagemSucesso;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.avaliacao.IAvaliacao;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AvaliacaoControllerTest extends BaseTest{

    @Mock
    private IAvaliacao avaliacaoCommand;

    @Mock
    private Gson gson;

    @InjectMocks
    private AvaliacaoController avaliacaoController;



    @Test
    void criarAvaliacaoRetornarFluxo200() {
        //manipular retorno (Criação de mock)
        Mockito.when(avaliacaoCommand.criarAvaliacao(Mockito.any(), anyString()))
                .thenReturn(1);

        //chamada
        ResponseEntity<?> retorno =
                avaliacaoController.criarAvaliacao(criarAvaliacaoRequestDTO(), "token" );
        var body = (StandardResponseDTO) retorno.getBody();
        IdResponseDTO id = (IdResponseDTO) body.getDados();

        //validação
        assertEquals(HttpStatus.OK, retorno.getStatusCode());
        assertEquals(MensagemSucesso.CRIAR_AVALIACAO.getMensagem(), body.getMessagem());
        assertEquals(1, id.getId());
    }

    @Test
    void criarNotaRetornarFluxo200() {
        Mockito.when(avaliacaoCommand.lancarNota(any(), anyString()))
                .thenReturn(1);

        ResponseEntity<?> retorno =
                avaliacaoController.criarNota(criarNotaRequestDTO(), "token" );
        var body = (StandardResponseDTO) retorno.getBody();
        IdResponseDTO id = (IdResponseDTO) body.getDados();

        assertEquals(HttpStatus.OK, retorno.getStatusCode());
        assertEquals(MensagemSucesso.CRIAR_NOTA.getMensagem(), body.getMessagem());
        assertEquals(1, id.getId());


    }

    @Test
    void listarAvaliacoesRetornarFluxo200() {
        List<AvaliacaoResponseDTO > lista = new ArrayList<>();
        AvaliacaoResponseDTO avaliacaoResponseDTO = AvaliacaoResponseDTO.builder()
                .idAvaliacao(1)
                .build();
        lista.add(avaliacaoResponseDTO);

        Mockito.when(avaliacaoCommand.listarAvaliacoes()).thenReturn(lista);

        ResponseEntity<?> retorno =
                avaliacaoController.listarAvaliacoes();
        var body = (StandardResponseDTO) retorno.getBody();
        List<AvaliacaoResponseDTO> listaRetorno = (List<AvaliacaoResponseDTO>) body.getDados();

        assertEquals(HttpStatus.OK, retorno.getStatusCode());
        assertEquals(MensagemSucesso.LISTAR_AVALIACAO.getMensagem(), body.getMessagem());
        assertEquals(lista, listaRetorno);


    }

    @Test
    void buscarNotaRetornarFluxo200() {

        NotaResponseDTO notaCommand = NotaResponseDTO.builder()
                .idNota(1)
                .nota(3.0)
                .dataAtribuicao(LocalDateTime.now())
                .entregaId(1)
                .feedback("Precisa melhorar").build();

        Mockito.when(avaliacaoCommand.buscarNota("token",1)).thenReturn(notaCommand);

        ResponseEntity<?> retorno =
                avaliacaoController.buscarNota("token", 1);

        var body = (StandardResponseDTO) retorno.getBody();

        NotaResponseDTO notaResponseDTO = (NotaResponseDTO) body.getDados();

        assertEquals(HttpStatus.OK, retorno.getStatusCode());
        assertEquals(MensagemSucesso.BUSCAR_NOTA.getMensagem(), body.getMessagem());
        assertEquals(notaCommand, notaResponseDTO);
    }

    @Test
    void atualizarNotaRetornarFluxo() {

        //chamada
        ResponseEntity<?> retorno =
                avaliacaoController.atualizarNota("Token", criarAtualizaNotaRequestDTO());
        var body = (StandardResponseDTO) retorno.getBody();
        IdResponseDTO id = (IdResponseDTO) body.getDados();

        //validação
        assertEquals(HttpStatus.OK, retorno.getStatusCode());
        assertEquals(MensagemSucesso.NOTA_ATUALIZADA.getMensagem(), body.getMessagem());
    }
}