package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.command;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega.dto.EntregaRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Entrega;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.MensagemErro;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.exceptions.NegocioException;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.entrega.IEntrega;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.output.entrega.IEntregaRepository;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.seguranca.ISegurancaConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EntregaCommand implements IEntrega {

    private final IEntregaRepository entregaRepository;
    private final ISegurancaConfig segurancaConfig;

    public EntregaCommand(IEntregaRepository entregaRepository, ISegurancaConfig segurancaConfig) {
        this.entregaRepository = entregaRepository;
        this.segurancaConfig = segurancaConfig;
    }

    @Override
    public Integer criarEntrega(EntregaRequestDTO entregaRequestDTO, String token) {
        int idAluno = segurancaConfig.buscarIdToken(token);

        if (!entregaRepository.existeAvaliacao(entregaRequestDTO.getIdAvaliacao())) {
            throw new NegocioException(MensagemErro.AVALIACAO_NAO_EXISTE.getMensagem());
        }

        if(!entregaRepository.validarTempoEntrega(LocalDateTime.now(), entregaRequestDTO.getIdAvaliacao())){
            throw new NegocioException(MensagemErro.TEMPO_ENTREGA_EXPIRADO.getMensagem());
        }

        if (entregaRepository.existeEntregaParaAvaliacao(entregaRequestDTO.getIdAvaliacao(), idAluno)) {
            throw new NegocioException(MensagemErro.ENTREGA_JA_EXISTE.getMensagem());
        }

        Entrega entrega = new Entrega(idAluno, entregaRequestDTO.getIdAvaliacao(), entregaRequestDTO.getConteudo());
        return entregaRepository.criarEntrega(entrega);
    }
}

