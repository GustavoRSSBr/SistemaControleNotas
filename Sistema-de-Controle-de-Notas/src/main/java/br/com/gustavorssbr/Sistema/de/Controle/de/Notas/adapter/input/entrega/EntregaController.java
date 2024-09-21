package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega.dto.EntregaRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.dto.IdResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.dto.StandardResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.entrega.IEntrega;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scn/entregas")
public class EntregaController implements  IEntregaController {

    @Autowired
    private IEntrega entregaService;
    @Override
    @PostMapping("/criar-entrega")
    public ResponseEntity<?> criarEntrega(@RequestBody EntregaRequestDTO entregaRequestDTO, @RequestHeader(value = "Authorization") String token) {
        IdResponseDTO responseId = new IdResponseDTO(entregaService.criarEntrega(entregaRequestDTO, token));
        return ResponseEntity.ok(
                StandardResponseDTO.builder()
                        .messagem("Entrega feita com sucesso!")
                        .dados(responseId)
                        .build()
        );
    }
}
