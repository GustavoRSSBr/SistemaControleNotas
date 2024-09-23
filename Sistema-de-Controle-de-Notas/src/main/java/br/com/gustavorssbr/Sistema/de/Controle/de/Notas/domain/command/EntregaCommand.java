package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.command;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega.dto.EntregaRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega.dto.EntregaResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Entrega;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.MensagemErro;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.exceptions.NegocioException;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.entrega.IEntrega;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.seguranca.ISegurancaConfig;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.output.entrega.IEntregaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

        if (!entregaRepository.existeAvaliacao(entregaRequestDTO.getIdAvaliacao())) {
            throw new NegocioException(MensagemErro.AVALIACAO_NAO_EXISTE.getMensagem());
        }

        if (!entregaRepository.validarTempoEntrega(LocalDateTime.now(), entregaRequestDTO.getIdAvaliacao())) {
            throw new NegocioException(MensagemErro.TEMPO_ENTREGA_EXPIRADO.getMensagem());
        }

        int idAluno = segurancaConfig.buscarIdToken(token);

        if (entregaRepository.existeEntregaParaAvaliacao(entregaRequestDTO.getIdAvaliacao(), idAluno)) {
            throw new NegocioException(MensagemErro.ENTREGA_JA_EXISTE.getMensagem());
        }

        Entrega entrega = new Entrega(idAluno, entregaRequestDTO.getIdAvaliacao(), entregaRequestDTO.getConteudo());
        return entregaRepository.criarEntrega(entrega);
    }

    @Override
    public List<EntregaResponseDTO> listarEntregasDaAvaliacao(String token, int idAvaliacao) {
        if (!entregaRepository.existeAvaliacao(idAvaliacao)) {
            throw new NegocioException(MensagemErro.AVALIACAO_NAO_EXISTE.getMensagem());
        }

        int idProfessor = segurancaConfig.buscarIdToken(token);

        if (!entregaRepository.verificarProfessorAvaliacao(idProfessor, idAvaliacao)) {
            throw new NegocioException(MensagemErro.PROFESSOR_SEM_PERMISSAO_ENTREGA.getMensagem());
        }

        List<EntregaResponseDTO> lista = entregaRepository.listarEntregasPorAvaliacao(idAvaliacao);

        if (lista.isEmpty()) {
            throw new NegocioException(MensagemErro.LISTA_ENTREGA_VAZIA.getMensagem());
        }

        return lista;
    }

}

