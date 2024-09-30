package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto.*;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.dto.IdResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.dto.StandardResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.MensagemSucesso;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.avaliacao.IAvaliacao;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scn")
public class AvaliacaoController implements IAvaliacaoController {

    @Autowired
    private IAvaliacao avaliacaoService;

    @Autowired
    private Gson gson;

    private static final Logger LOGGER = LoggerFactory.getLogger(AvaliacaoController.class);

    @PostMapping("/professores/criar-avaliacao")
    @Override
    public ResponseEntity<?> criarAvaliacao(@RequestBody AvaliacaoRequestDTO avaliacaoRequestDTO, @RequestHeader(value = "Authorization") String token) {
        LOGGER.info("Início do método criarAvaliacao com request: {} {}", gson.toJson(avaliacaoRequestDTO), token);
        long startTime = System.currentTimeMillis();

        IdResponseDTO responseId = new IdResponseDTO(avaliacaoService.criarAvaliacao(avaliacaoRequestDTO, token));

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        LOGGER.info("Tempo decorrido: " + elapsedTime + " milissegundos");

        return ResponseEntity.ok(
                StandardResponseDTO.builder()
                        .messagem(MensagemSucesso.CRIAR_AVALIACAO.getMensagem())
                        .dados(responseId)
                        .build()
        );
    }
    @PostMapping("/professores/lancar-nota")
    @Override
    public ResponseEntity<?> criarNota(@RequestBody NotaRequestDTO notaRequestDTO, @RequestHeader(value = "Authorization") String token) {
        LOGGER.info("Início do método criarNota com request: {} {}", gson.toJson(notaRequestDTO), token);
        long startTime = System.currentTimeMillis();

        IdResponseDTO responseId = new IdResponseDTO(avaliacaoService.lancarNota(notaRequestDTO, token));

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        LOGGER.info("Tempo decorrido: " + elapsedTime + " milissegundos");

        return ResponseEntity.ok(
                StandardResponseDTO.builder()
                        .messagem(MensagemSucesso.CRIAR_NOTA.getMensagem())
                        .dados(responseId)
                        .build()
        );
    }

    @GetMapping("/listar-avaliacoes")
    @Override
    public ResponseEntity<?> listarAvaliacoes() {
        LOGGER.info("Início do método listarAvaliacoes.");
        long startTime = System.currentTimeMillis();

        List<AvaliacaoResponseDTO> avaliacoes = avaliacaoService.listarAvaliacoes();

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        LOGGER.info("Tempo decorrido: " + elapsedTime + " milissegundos");

        return ResponseEntity.ok(
                StandardResponseDTO.builder()
                        .messagem(MensagemSucesso.LISTAR_AVALIACAO.getMensagem())
                        .dados(avaliacoes)
                        .build()
        );
    }

    @Override
    @GetMapping("/alunos/buscar-nota")
    public ResponseEntity<?> buscarNota(@RequestHeader(value = "Authorization") String token, @RequestParam int idEntrega) {
        LOGGER.info("Início do método buscarNota com request: {} {}", token, idEntrega);
        long startTime = System.currentTimeMillis();

        NotaResponseDTO nota = avaliacaoService.buscarNota(token, idEntrega);

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        LOGGER.info("Tempo decorrido: " + elapsedTime + " milissegundos");

        return ResponseEntity.ok(
                StandardResponseDTO.builder()
                        .messagem(MensagemSucesso.BUSCAR_NOTA.getMensagem())
                        .dados(nota)
                        .build()
        );
    }

    @Override
    @PatchMapping("/professores/atualizar-nota")
    public ResponseEntity<?> atualizarNota(@RequestHeader(value = "Authorization") String token, @RequestBody AtualizaNotaRequestDTO novaNota) {
        LOGGER.info("Início do método atualizarNota com request: {} {}", token, gson.toJson(novaNota));
        long startTime = System.currentTimeMillis();

        avaliacaoService.atualizarNota(token, novaNota);

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        LOGGER.info("Tempo decorrido: " + elapsedTime + " milissegundos");

        return ResponseEntity.ok(
                StandardResponseDTO.builder()
                        .messagem(MensagemSucesso.NOTA_ATUALIZADA.getMensagem())
                        .build()
        );
    }
}
