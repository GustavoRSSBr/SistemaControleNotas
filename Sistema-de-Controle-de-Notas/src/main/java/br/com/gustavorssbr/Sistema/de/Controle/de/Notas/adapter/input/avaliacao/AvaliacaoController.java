package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto.AvaliacaoRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto.AvaliacaoResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto.NotaRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto.NotaResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.dto.IdResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.dto.StandardResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.avaliacao.IAvaliacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scn")
public class AvaliacaoController implements IAvaliacaoController {

    @Autowired
    private IAvaliacao avaliacaoService;

    @PostMapping("/professores/criar-avaliacao")
    @Override
    public ResponseEntity<?> criarAvaliacao(@RequestBody AvaliacaoRequestDTO avaliacaoRequestDTO, @RequestHeader(value = "Authorization") String token) {
        IdResponseDTO responseId = new IdResponseDTO(avaliacaoService.criarAvaliacao(avaliacaoRequestDTO, token));
        return ResponseEntity.ok(
                StandardResponseDTO.builder()
                        .messagem("Avaliação criada com sucesso!")
                        .dados(responseId)
                        .build()
        );
    }
    @PostMapping("/professores/lancar-nota")
    @Override
    public ResponseEntity<?> criarNota(@RequestBody NotaRequestDTO notaRequestDTO, @RequestHeader(value = "Authorization") String token) {
        IdResponseDTO responseId = new IdResponseDTO(avaliacaoService.lancarNota(notaRequestDTO, token));
        return ResponseEntity.ok(
                StandardResponseDTO.builder()
                        .messagem("Nota lançada com sucesso!")
                        .dados(responseId)
                        .build()
        );
    }

    @GetMapping("/listar-avaliacoes")
    @Override
    public ResponseEntity<?> listarAvaliacoes() {
        List<AvaliacaoResponseDTO> avaliacoes = avaliacaoService.listarAvaliacoes();
        return ResponseEntity.ok(
                StandardResponseDTO.builder()
                        .messagem("Avaliações encontradas com sucesso!")
                        .dados(avaliacoes)
                        .build()
        );
    }

    @Override
    @GetMapping("/alunos/buscar-nota")
    public ResponseEntity<?> buscarNota(@RequestHeader(value = "Authorization") String token, @RequestParam int idEntrega) {
        NotaResponseDTO nota = avaliacaoService.buscarNota(token, idEntrega);
        return ResponseEntity.ok(
                StandardResponseDTO.builder()
                        .messagem("Nota encontrada com sucesso!")
                        .dados(nota)
                        .build()
        );
    }
}
