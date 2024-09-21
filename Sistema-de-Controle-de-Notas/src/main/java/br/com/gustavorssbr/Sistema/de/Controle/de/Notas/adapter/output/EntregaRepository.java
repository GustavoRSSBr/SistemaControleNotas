package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.output;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Entrega;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.output.entrega.IEntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

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
}

