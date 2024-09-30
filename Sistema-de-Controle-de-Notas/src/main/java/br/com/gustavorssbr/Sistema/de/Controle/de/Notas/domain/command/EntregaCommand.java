package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.command;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega.dto.EntregaRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega.dto.EntregaResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Entrega;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.MensagemErro;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.exceptions.NegocioException;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.entrega.IEntrega;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.seguranca.ISegurancaConfig;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.output.entrega.IEntregaRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EntregaCommand implements IEntrega {

    private final IEntregaRepository entregaRepository;
    private final ISegurancaConfig segurancaConfig;
    private final Gson gson;

    private static final Logger LOGGER = LoggerFactory.getLogger(EntregaCommand.class);

    public EntregaCommand(IEntregaRepository entregaRepository, ISegurancaConfig segurancaConfig, Gson gson) {
        this.entregaRepository = entregaRepository;
        this.segurancaConfig = segurancaConfig;
        this.gson = gson;
    }

    @Override
    public Integer criarEntrega(EntregaRequestDTO entregaRequestDTO, String token) {
        LOGGER.info("Início do método criarEntrega com request: {} {}", gson.toJson(entregaRequestDTO), token);

        LOGGER.info("Verificando se existe a avaliação");
        if (!entregaRepository.existeAvaliacao(entregaRequestDTO.getIdAvaliacao())) {
            throw new NegocioException(MensagemErro.AVALIACAO_NAO_EXISTE.getMensagem());
        }

        LOGGER.info("Validando tempo da entrega");
        if (!entregaRepository.validarTempoEntrega(LocalDateTime.now(), entregaRequestDTO.getIdAvaliacao())) {
            throw new NegocioException(MensagemErro.TEMPO_ENTREGA_EXPIRADO.getMensagem());
        }

        LOGGER.info("Buscando informações do usuario");
        int idAluno = segurancaConfig.buscarIdToken(token);

        LOGGER.info("Verificando se já existe uma entrega para a avaliacao");
        if (entregaRepository.existeEntregaParaAvaliacao(entregaRequestDTO.getIdAvaliacao(), idAluno)) {
            throw new NegocioException(MensagemErro.ENTREGA_JA_EXISTE.getMensagem());
        }

        LOGGER.info("Validando informações da entrega");
        validarInformacoesEntrega(entregaRequestDTO);

        LOGGER.info("Construindo entrega");
        Entrega entrega = Entrega.builder()
                .idAluno(idAluno)
                .idAvaliacao(entregaRequestDTO.getIdAvaliacao())
                .conteudo(entregaRequestDTO.getConteudo())
                .build();

        return entregaRepository.criarEntrega(entrega);
    }

    private void validarInformacoesEntrega(EntregaRequestDTO entregaRequestDTO) {
        if (entregaRequestDTO.getIdAvaliacao() == null) {
            throw new NegocioException(MensagemErro.ID_INVALIDO_AVALIACAO.getMensagem());
        }
        if (entregaRequestDTO.getConteudo() == null || entregaRequestDTO.getConteudo().trim().isEmpty()) {
            throw new NegocioException(MensagemErro.CONTEUDO_VAZIO.getMensagem());
        }
    }

    @Override
    public List<EntregaResponseDTO> listarEntregasDaAvaliacao(String token, int idAvaliacao) {
        LOGGER.info("Início do método listarEntregasDaAvaliacao com request: {} {}", token, idAvaliacao);

        LOGGER.info("Verificando se existe a avaliacao");
        if (!entregaRepository.existeAvaliacao(idAvaliacao)) {
            throw new NegocioException(MensagemErro.AVALIACAO_NAO_EXISTE.getMensagem());
        }

        LOGGER.info("Buscando informações do usuario");
        int idProfessor = segurancaConfig.buscarIdToken(token);

        LOGGER.info("Verificando se a avaliacao pertence ao usuario");
        if (!entregaRepository.verificarProfessorAvaliacao(idProfessor, idAvaliacao)) {
            throw new NegocioException(MensagemErro.PROFESSOR_SEM_PERMISSAO_ENTREGA.getMensagem());
        }

        LOGGER.info("Listando entregas");
        List<EntregaResponseDTO> lista = entregaRepository.listarEntregasPorAvaliacao(idAvaliacao);

        if (lista.isEmpty()) {
            throw new NegocioException(MensagemErro.LISTA_ENTREGA_VAZIA.getMensagem());
        }

        return lista;
    }

}

