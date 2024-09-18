package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.output;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Entrega;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.output.entrega.IEntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EntregaRepository implements IEntregaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer criarEntrega(Entrega entrega) {
        String sql = "SELECT CRIAR_ENTREGA(?, ?, ?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{
                entrega.getIdAluno(),
                entrega.getIdAvaliacao(),
                entrega.getConteudo()
        }, Integer.class);
    }

    @Override
    public boolean existeAvaliacao(int idAvaliacao) {
        String sql = "SELECT EXISTE_AVALIACAO(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{idAvaliacao}, Boolean.class);
    }

    @Override
    public boolean existeEntregaParaAvaliacao(int idAvaliacao, int idAluno) {
        String sql = "SELECT EXISTE_ENTREGA_PARA_AVALIACAO(?, ?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{idAvaliacao, idAluno}, Boolean.class);
    }
}

