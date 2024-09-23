package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.output;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega.dto.EntregaResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Entrega;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.output.entrega.IEntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class EntregaRepository implements IEntregaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer criarEntrega(Entrega entrega) {
        String sql = "SELECT CRIAR_ENTREGA(?, ?, ?)";
        return jdbcTemplate.queryForObject(sql, Integer.class,
                entrega.getIdAluno(),
                entrega.getIdAvaliacao(),
                entrega.getConteudo()
        );
    }

    @Override
    public boolean existeAvaliacao(int idAvaliacao) {
        String sql = "SELECT EXISTE_AVALIACAO(?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, idAvaliacao);
    }

    @Override
    public boolean existeEntregaParaAvaliacao(int idAvaliacao, int idAluno) {
        String sql = "SELECT EXISTE_ENTREGA_PARA_AVALIACAO(?, ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, idAvaliacao, idAluno);
    }

    @Override
    public boolean validarTempoEntrega(LocalDateTime dataEntrega, int idAvaliavao) {
        String sql = "SELECT VALIDAR_TEMPO_DA_ENTREGA(?, ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, dataEntrega, idAvaliavao);
    }

    @Override
    public boolean verificarProfessorAvaliacao(int professorId, int avaliacaoId) {
        String sql = "SELECT VERIFICAR_PROFESSOR_AVALIACAO(?, ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, professorId, avaliacaoId);
    }

    @Override
    public List<EntregaResponseDTO> listarEntregasPorAvaliacao(int avaliacaoId) {
        String sql = "SELECT * FROM LISTAR_ENTREGAS_POR_AVALIACAO(?)";

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(EntregaResponseDTO.class),
                avaliacaoId
        );
    }
}

