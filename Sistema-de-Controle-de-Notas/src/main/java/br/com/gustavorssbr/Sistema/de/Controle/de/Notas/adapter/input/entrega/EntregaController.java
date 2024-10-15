package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega.dto.EntregaRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega.dto.EntregaResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.dto.IdResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.dto.StandardResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.MensagemSucesso;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.entrega.IEntrega;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scn/entregas")
public class EntregaController implements  IEntregaController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EntregaController.class);

    @Autowired
    private IEntrega entregaService;
    @Override
    @PostMapping("/alunos/criar-entrega")
    public ResponseEntity<?> criarEntrega(@RequestBody EntregaRequestDTO entregaRequestDTO, @RequestHeader(value = "Authorization") String token) {
        LOGGER.info("Início do método criarEntrega com request: {} {}", entregaRequestDTO, token);
        long startTime = System.currentTimeMillis();

        IdResponseDTO responseId = new IdResponseDTO(entregaService.criarEntrega(entregaRequestDTO, token));

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        LOGGER.info("Tempo decorrido: " + elapsedTime + " milissegundos");

        return ResponseEntity.ok(
                StandardResponseDTO.builder()
                        .messagem(MensagemSucesso.CRIAR_ENTREGA.getMensagem())
                        .dados(responseId)
                        .build()
        );
    }

    @Override
    @GetMapping("/professores/listar-entregas")
    public ResponseEntity<?> listarEntregas( @RequestHeader(value = "Authorization") String token, @RequestParam int idAvaliacao) {
        LOGGER.info("Início do método listarEntregas com request: {} {}", token, idAvaliacao);
        long startTime = System.currentTimeMillis();

        List<EntregaResponseDTO> lista = entregaService.listarEntregasDaAvaliacao(token, idAvaliacao);

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        LOGGER.info("Tempo decorrido: " + elapsedTime + " milissegundos");

        return ResponseEntity.ok(
                StandardResponseDTO.builder()
                        .messagem(MensagemSucesso.LISTAR_ENTREGA.getMensagem())
                        .dados(lista)
                        .build()
        );
    }
}
