package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.infrastructure.interfaceAdapters.controllers.impl;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.dtos.request.AvaliacaoDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.services.IAvaliacaoService;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.infrastructure.interfaceAdapters.controllers.IAvaliacaoController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scn")
public class AvaliacaoControllerImpl implements IAvaliacaoController {

    @Autowired
    private IAvaliacaoService avaliacaoService;
    @Override
    @PostMapping("/professores/criar-avaliacao")
    public ResponseEntity<Integer> criarAvaliacao(AvaliacaoDTO avaliacaoDTO, String token) {
        Integer idAvaliacao = avaliacaoService.criarAvaliacao(avaliacaoDTO, token);
        return ResponseEntity.ok(idAvaliacao);
    }
}
