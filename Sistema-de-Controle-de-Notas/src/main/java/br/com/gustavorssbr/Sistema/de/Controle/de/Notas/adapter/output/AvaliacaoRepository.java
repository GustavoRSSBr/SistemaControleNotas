package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.output;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto.AtualizaNotaRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto.AvaliacaoResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto.NotaResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Avaliacao;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Nota;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.output.avaliacao.IAvaliacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AvaliacaoRepository implements IAvaliacaoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(AvaliacaoRepository.class);

    @Override
    public Integer criarAvaliacao(Avaliacao avaliacao) {
        LOGGER.info("Início do método criarAvaliacao com request de dados da avaliacao");
            String sql = "SELECT CRIAR_AVALIACAO(?, ?, ?, ?)";
            return jdbcTemplate.queryForObject(sql, Integer.class,
                    avaliacao.getTitulo(),
                    avaliacao.getDescricao(),
                    avaliacao.getDataEntrega(),
                    avaliacao.getProfessorId()
            );
    }


    @Override
    public boolean existeEntrega(int idEntrega) {
        LOGGER.info("Início do método existeEntrega com request: {}", idEntrega);

        String sql = "SELECT EXISTE_ENTREGA(?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, idEntrega);
    }

    @Override
    public boolean validarRelacaoEntregaComProfessor(int idProfessor, Integer idEntrega) {
        LOGGER.info("Início do método validarRelacaoEntregaComProfessor com request: {} {}", idProfessor ,idEntrega);

        String sql = "SELECT RELACAO_ENTREGA_PROFESSOR(?, ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, idProfessor, idEntrega);
    }

    @Override
    public Integer lancarNota(Nota nota) {
        LOGGER.info("Início do método lancarNota com request: {}", nota);

        String sql = "SELECT LANCAR_NOTA(?, ?, ?)";
        return jdbcTemplate.queryForObject(sql, Integer.class,
                nota.getIdEntrega(),
                nota.getNota(),
                nota.getFeedback()
        );
    }

    @Override
    public boolean existeNotaNaEntrega(int idEntrega) {
        LOGGER.info("Início do método existeNotaNaEntrega com request: {}", idEntrega);

        String sql = "SELECT EXISTE_NOTA_NA_ENTREGA(?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, idEntrega);
    }

    @Override
    public List<AvaliacaoResponseDTO> listarAvaliacoes() {
        LOGGER.info("Início do método listarAvaliacoes");

        String sql = "SELECT * FROM LISTAR_AVALIACOES()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(AvaliacaoResponseDTO.class));
    }

    @Override
    public boolean verificarEntregaAssociada(int idAluno, int idEntrega) {
        LOGGER.info("Início do método verificarEntregaAssociada com request: {}", idEntrega);

        String sql = "SELECT VERIFICAR_ENTREGA_ASSOCIADA(?, ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, idAluno, idEntrega);
    }

    @Override
    public NotaResponseDTO retornarInformacoesNotaPorEntrega(int idEntrega) {
        LOGGER.info("Início do método retornarInformacoesNotaPorEntrega com request: {}", idEntrega);

        String sql = "SELECT * FROM RETORNAR_INFORMACOES_NOTA_ENTREGA(?)";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(NotaResponseDTO.class), idEntrega);
    }

    @Override
    public void atualizarNota(AtualizaNotaRequestDTO novaNota) {
        LOGGER.info("Início do método atualizarNota com request: {}", novaNota);

        String sql = "CALL ATUALIZAR_NOTA(?, ?, ?)";
        jdbcTemplate.update(sql,
                novaNota.getIdNota(),
                novaNota.getValorNota(),
                novaNota.getFeedback()
        );
    }

    @Override
    public boolean existeNota(int idNota) {
        LOGGER.info("Início do método existeNota com request: {}", idNota);

        String sql = "SELECT EXISTE_NOTA(?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, idNota);
    }

    @Override
    public boolean existeRelacaoProfessorNota(int idProfessor,int idNota) {
        LOGGER.info("Início do método existeNota com request: {}", idNota);

        String sql = "SELECT NOTA_RELACAO_PROFESSOR(?, ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, idProfessor, idNota);
    }
}
