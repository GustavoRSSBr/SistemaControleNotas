package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.command;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto.AvaliacaoRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto.NotaRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Avaliacao;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Nota;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.MensagemErro;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.exceptions.NegocioException;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.avaliacao.IAvaliacao;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.seguranca.ISegurancaConfig;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.output.avaliacao.IAvaliacaoRepository;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.utils.DataUtil;
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
    public Integer criarAvaliacao(AvaliacaoRequestDTO avaliacaoRequestDTO, String token) {
        int idProfessor = segurancaConfig.buscarIdToken(token);
        LocalDateTime dataEntrega = DataUtil.converteStringParaData(avaliacaoRequestDTO.getDataEntrega());
        Avaliacao avaliacao = new Avaliacao(
                avaliacaoRequestDTO.getTitulo(),
                avaliacaoRequestDTO.getDescricao(),
                dataEntrega,
                idProfessor
        );

        return avaliacaoRepository.criarAvaliacao(avaliacao);
    }

    @Override
    public Integer lancarNota(NotaRequestDTO notaRequestDTO, String token) {
        int idProfessor = segurancaConfig.buscarIdToken(token);

        if (!avaliacaoRepository.existeEntrega(notaRequestDTO.getIdEntrega())) {
            throw new NegocioException(MensagemErro.ENTREGA_NAO_EXISTE.getMensagem());
        }

        if (!avaliacaoRepository.validarRelacaoEntregaComProfessor(idProfessor, notaRequestDTO.getIdEntrega())) {
            throw new NegocioException(MensagemErro.ENTREGA_NAO_PERTENCE_AO_PROFESSOR.getMensagem());
        }

        if (avaliacaoRepository.existeNotaNaEntrega(notaRequestDTO.getIdEntrega())) {
            throw new NegocioException(MensagemErro.JA_EXISTE_NOTA.getMensagem());
        }



        Nota nota = new Nota(
                notaRequestDTO.getIdEntrega(),
                notaRequestDTO.getValorNota(),
                notaRequestDTO.getFeedback()
        );

        return avaliacaoRepository.lancarNota(nota);
    }

}
