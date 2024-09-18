package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto.AvaliacaoDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.avaliacao.IAvaliacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scn")
public class AvaliacaoController implements IAvaliacaoController {

    @Autowired
    private IAvaliacao avaliacaoService;

    @PostMapping("/professores/criar-avaliacao")
    public ResponseEntity<Integer> criarAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDTO, @RequestHeader(value = "Authorization") String token) {
        Integer idAvaliacao = avaliacaoService.criarAvaliacao(avaliacaoDTO, token);
        return ResponseEntity.ok(idAvaliacao);
    }
}
