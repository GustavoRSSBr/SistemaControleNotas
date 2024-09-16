package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.services.impl;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.dtos.request.AvaliacaoDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.services.IAvaliacaoService;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.utils.DataUtil;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Avaliacao;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.repositories.IAvaliacaoRepository;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.infrastructure.utils.JwtUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AvaliacaoServiceImpl implements IAvaliacaoService {

    private final IAvaliacaoRepository avaliacaoRepository;

    public AvaliacaoServiceImpl(IAvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    @Override
    public Integer criarAvaliacao(AvaliacaoDTO avaliacaoDTO, String token) {
        int idProfessor = JwtUtil.decodeToken(token.replace("Bearer ", "")).getId();

        LocalDateTime dataEntrega = DataUtil.converteStringParaData(avaliacaoDTO.getDataEntrega());

        Avaliacao avaliacao = new Avaliacao(
                avaliacaoDTO.getTitulo(),
                avaliacaoDTO.getDescricao(),
                dataEntrega,
                idProfessor
        );

        return avaliacaoRepository.criarAvaliacao(avaliacao);
    }

}
