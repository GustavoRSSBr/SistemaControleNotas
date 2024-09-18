package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.command;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto.AvaliacaoDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.avaliacao.IAvaliacao;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.util.DataUtil;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Avaliacao;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.output.avaliacao.IAvaliacaoRepository;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.seguranca.ISegurancaConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AvaliacaoCommand implements IAvaliacao {

    private final IAvaliacaoRepository avaliacaoRepository;
    private final ISegurancaConfig segurancaConfig;

    public AvaliacaoCommand(IAvaliacaoRepository avaliacaoRepository, ISegurancaConfig segurancaConfig) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.segurancaConfig = segurancaConfig;
    }

    @Override
    public Integer criarAvaliacao(AvaliacaoDTO avaliacaoDTO, String token) {
        int idProfessor = segurancaConfig.buscarIdToken(token);
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
