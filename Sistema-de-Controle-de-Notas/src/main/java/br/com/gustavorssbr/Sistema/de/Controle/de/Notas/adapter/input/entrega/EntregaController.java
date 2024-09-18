package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega.dto.EntregaDTO;
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
    public ResponseEntity<Integer> criarEntrega(@RequestBody EntregaDTO entregaDTO, @RequestHeader(value = "Authorization") String token) {
        Integer idEntrega = entregaService.criarEntrega(entregaDTO, token);
        return ResponseEntity.ok(idEntrega);
    }
}
