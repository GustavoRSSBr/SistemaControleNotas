package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega.dto.EntregaRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega.dto.EntregaResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.dto.IdResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.dto.StandardResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.MensagemSucesso;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.entrega.IEntrega;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scn/entregas")
public class EntregaController implements  IEntregaController {

    @Autowired
    private IEntrega entregaService;
    @Override
    @PostMapping("/alunos/criar-entrega")
    public ResponseEntity<?> criarEntrega(@RequestBody EntregaRequestDTO entregaRequestDTO, @RequestHeader(value = "Authorization") String token) {
        IdResponseDTO responseId = new IdResponseDTO(entregaService.criarEntrega(entregaRequestDTO, token));
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
        List<EntregaResponseDTO> lista = entregaService.listarEntregasDaAvaliacao(token, idAvaliacao);
        return ResponseEntity.ok(
                StandardResponseDTO.builder()
                        .messagem(MensagemSucesso.LISTAR_ENTREGA.getMensagem())
                        .dados(lista)
                        .build()
        );
    }
}
