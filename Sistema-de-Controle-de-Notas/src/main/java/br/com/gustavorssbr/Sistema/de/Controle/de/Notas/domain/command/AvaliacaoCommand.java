package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.command;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto.*;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Avaliacao;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Nota;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.MensagemErro;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.exceptions.NegocioException;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.avaliacao.IAvaliacao;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.seguranca.ISegurancaConfig;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.output.avaliacao.IAvaliacaoRepository;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.utils.DataUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AvaliacaoCommand implements IAvaliacao {


    private final IAvaliacaoRepository avaliacaoRepository;
    private final ISegurancaConfig segurancaConfig;

    private final Gson gson;

    private static final Logger LOGGER = LoggerFactory.getLogger(AvaliacaoCommand.class);

    public AvaliacaoCommand(IAvaliacaoRepository avaliacaoRepository, ISegurancaConfig segurancaConfig, Gson gson) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.segurancaConfig = segurancaConfig;
        this.gson = gson;

    }

    @Override
    public Integer criarAvaliacao(AvaliacaoRequestDTO avaliacaoRequestDTO, String token) {
        LOGGER.info("Início do método criarAvaliacao com request: {} {}", gson.toJson(avaliacaoRequestDTO), token);

        LOGGER.info("Buscando informções do usuario");
        int idProfessor = segurancaConfig.buscarIdToken(token);

        LOGGER.info("Convertendo data para o padrão LocalDateTime");
        LocalDateTime dataEntrega = DataUtil.converteStringParaData(avaliacaoRequestDTO.getDataEntrega());

        LOGGER.info("Validando informações da avaliação");
        validarInformacoesAvaliacao(avaliacaoRequestDTO, dataEntrega);

        LOGGER.info("Construindo a avaliação");
        Avaliacao avaliacao = Avaliacao.builder()
                .titulo(avaliacaoRequestDTO.getTitulo())
                .descricao(avaliacaoRequestDTO.getDescricao())
                .dataEntrega(dataEntrega)
                .professorId(idProfessor)
                .build();

        return avaliacaoRepository.criarAvaliacao(avaliacao);
    }

    private void validarInformacoesAvaliacao(AvaliacaoRequestDTO avaliacaoRequestDTO, LocalDateTime dateEntrega) {
        if (dateEntrega.isBefore(LocalDateTime.now())) {
            throw new NegocioException(MensagemErro.DATA_ANTERIOR_ATUAL.getMensagem());
        }

        if (avaliacaoRequestDTO.getTitulo() == null || avaliacaoRequestDTO.getTitulo().trim().isEmpty()) {
            throw new NegocioException(MensagemErro.TITULO_NULO.getMensagem());
        }
    }

    @Override
    public Integer lancarNota(NotaRequestDTO notaRequestDTO, String token) {
        LOGGER.info("Início do método lancarNota com request: {} {}", gson.toJson(notaRequestDTO), token);

        LOGGER.info("Buscando informações do usuário");
        int idProfessor = segurancaConfig.buscarIdToken(token);

        LOGGER.info("Verificando se existe entrega");
        if (!avaliacaoRepository.existeEntrega(notaRequestDTO.getIdEntrega())) {
            throw new NegocioException(MensagemErro.ENTREGA_NAO_EXISTE.getMensagem());
        }

        LOGGER.info("Validando se a entrega tem relação com a avaliação do professor");
        if (!avaliacaoRepository.validarRelacaoEntregaComProfessor(idProfessor, notaRequestDTO.getIdEntrega())) {
            throw new NegocioException(MensagemErro.ENTREGA_NAO_PERTENCE_AO_PROFESSOR.getMensagem());
        }

        LOGGER.info("Validando se já existe uma nota na entrega");
        if (avaliacaoRepository.existeNotaNaEntrega(notaRequestDTO.getIdEntrega())) {
            throw new NegocioException(MensagemErro.JA_EXISTE_NOTA.getMensagem());
        }

        LOGGER.info("Validando informações da nota");
        validarInformacoesNota(notaRequestDTO);

        LOGGER.info("Construindo nota");
        Nota nota = Nota.builder()
                .idEntrega(notaRequestDTO.getIdEntrega())
                .nota(notaRequestDTO.getValorNota())
                .feedback(notaRequestDTO.getFeedback())
                .build();

        return avaliacaoRepository.lancarNota(nota);
    }

    private void validarInformacoesNota(NotaRequestDTO notaRequestDTO) {
        if(notaRequestDTO.getValorNota() == null || notaRequestDTO.getValorNota() > 10 || notaRequestDTO.getValorNota() < 0){
            throw new NegocioException(MensagemErro.NOTA_INVALIDA.getMensagem());
        }
    }

    @Override
    public List<AvaliacaoResponseDTO> listarAvaliacoes(){
        LOGGER.info("Início do método listarAvaliacoes");

        LOGGER.info("Buscando avaliações");
        List<AvaliacaoResponseDTO> avaliacaoes = avaliacaoRepository.listarAvaliacoes();

        LOGGER.info("Verificando se contém avaliações");
        if(avaliacaoes.isEmpty()){
            throw new NegocioException(MensagemErro.SEM_AVALIACOES.getMensagem());
        }

        return avaliacaoes;
    }

    @Override
    public NotaResponseDTO buscarNota(String token, int idEntrega){
        LOGGER.info("Início do método buscarNota com request: {} {}", token, idEntrega);

        LOGGER.info("Verificando se existe a entrega");
        if (!avaliacaoRepository.existeEntrega(idEntrega)){
            throw new NegocioException(MensagemErro.ENTREGA_NAO_EXISTE.getMensagem());
        }

        LOGGER.info("Buscando informações do usuario");
        int idAluno = segurancaConfig.buscarIdToken(token);

        LOGGER.info("Verificando se a entrega esta associada ao usuario");
        if (!avaliacaoRepository.verificarEntregaAssociada(idAluno, idEntrega)){
            throw new NegocioException(MensagemErro.ALUNO_SEM_PERMISSAO_NOTA.getMensagem());
        }

        LOGGER.info("Verificando se existe nota na entrega");
        if (!avaliacaoRepository.existeNotaNaEntrega(idEntrega)) {
            throw new NegocioException(MensagemErro.NAO_EXISTE_NOTA_ENTREGA.getMensagem());
        }

        return avaliacaoRepository.retornarInformacoesNotaPorEntrega(idEntrega);
    }

    @Override
    public void atualizarNota(String token, AtualizaNotaRequestDTO novaNota){
        LOGGER.info("Início do método atualizarNota com request: {} {}",token, gson.toJson(novaNota));

        LOGGER.info("Verificando se existe a nota");
        if(!avaliacaoRepository.existeNota(novaNota.getIdNota())){
            throw new NegocioException(MensagemErro.NAO_EXISTE_NOTA.getMensagem());
        }

        LOGGER.info("Buscando informações do usuário");
        int idProfessor = segurancaConfig.buscarIdToken(token);

        LOGGER.info("Verificando se a nota esta associada a uma entrega de uma avalição vinculada ao professor");
        if(!avaliacaoRepository.existeRelacaoProfessorNota(idProfessor, novaNota.getIdNota())){
            throw new NegocioException(MensagemErro.PROFESSOR_SEM_PERMISSAO_NOTA.getMensagem());
        }

        LOGGER.info("Validando informações da nota");
        if(novaNota.getValorNota() == null || novaNota.getValorNota() > 10 || novaNota.getValorNota() < 0){
            throw new NegocioException(MensagemErro.NOTA_INVALIDA.getMensagem());
        }


        avaliacaoRepository.atualizarNota(novaNota);
    }

}
