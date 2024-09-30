package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.output;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega.dto.EntregaResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Entrega;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.output.entrega.IEntregaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class EntregaRepository implements IEntregaRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntregaRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer criarEntrega(Entrega entrega) {
        LOGGER.info("Início do método criarAvaliacao com request de dados da entrega");

        String sql = "SELECT CRIAR_ENTREGA(?, ?, ?)";
        return jdbcTemplate.queryForObject(sql, Integer.class,
                entrega.getIdAluno(),
                entrega.getIdAvaliacao(),
                entrega.getConteudo()
        );
    }

    @Override
    public boolean existeAvaliacao(int idAvaliacao) {
        LOGGER.info("Início do método existeAvaliacao com request: {}", idAvaliacao);

        String sql = "SELECT EXISTE_AVALIACAO(?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, idAvaliacao);
    }

    @Override
    public boolean existeEntregaParaAvaliacao(int idAvaliacao, int idAluno) {
        LOGGER.info("Início do método existeEntregaParaAvaliacao com request: {}", idAvaliacao);

        String sql = "SELECT EXISTE_ENTREGA_PARA_AVALIACAO(?, ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, idAvaliacao, idAluno);
    }

    @Override
    public boolean validarTempoEntrega(LocalDateTime dataEntrega, int idAvaliavao) {
        LOGGER.info("Início do método validarTempoEntrega com request: {}", dataEntrega);

        String sql = "SELECT VALIDAR_TEMPO_DA_ENTREGA(?, ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, dataEntrega, idAvaliavao);
    }

    @Override
    public boolean verificarProfessorAvaliacao(int professorId, int avaliacaoId) {
        LOGGER.info("Início do método verificarProfessorAvaliacao com request: {}", avaliacaoId);

        String sql = "SELECT VERIFICAR_PROFESSOR_AVALIACAO(?, ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, professorId, avaliacaoId);
    }

    @Override
    public List<EntregaResponseDTO> listarEntregasPorAvaliacao(int avaliacaoId) {
        LOGGER.info("Início do método listarEntregasPorAvaliacao");

        String sql = "SELECT * FROM LISTAR_ENTREGAS_POR_AVALIACAO(?)";
        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(EntregaResponseDTO.class),
                avaliacaoId
        );
    }


}

