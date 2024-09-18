package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.command;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega.dto.EntregaDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Entrega;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.MensagemErro;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.exceptions.NegocioException;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.entrega.IEntrega;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.output.entrega.IEntregaRepository;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.seguranca.ISegurancaConfig;
import org.springframework.stereotype.Service;

@Service
public class EntregaCommand implements IEntrega {

    private final IEntregaRepository entregaRepository;
    private final ISegurancaConfig segurancaConfig;

    public EntregaCommand(IEntregaRepository entregaRepository, ISegurancaConfig segurancaConfig) {
        this.entregaRepository = entregaRepository;
        this.segurancaConfig = segurancaConfig;
    }

    @Override
    public Integer criarEntrega(EntregaDTO entregaDTO, String token) {
        int idAluno = segurancaConfig.buscarIdToken(token);

        if (!entregaRepository.existeAvaliacao(entregaDTO.getIdAvaliacao())) {
            throw new NegocioException(MensagemErro.AVALIACAO_NAO_EXISTE.getMensagem());
        }

        if (entregaRepository.existeEntregaParaAvaliacao(entregaDTO.getIdAvaliacao(), idAluno)) {
            throw new NegocioException(MensagemErro.ENTREGA_JA_EXISTE.getMensagem());
        }

        Entrega entrega = new Entrega(idAluno, entregaDTO.getIdAvaliacao(), entregaDTO.getConteudo());
        return entregaRepository.criarEntrega(entrega);
    }
}

