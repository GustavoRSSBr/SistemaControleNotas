package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.output;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Avaliacao;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.output.avaliacao.IAvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AvaliacaoRepository implements IAvaliacaoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer criarAvaliacao(Avaliacao avaliacao) {
        String sql = "SELECT CRIAR_AVALIACAO(?, ?, ?, ?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{
                avaliacao.getTitulo(),
                avaliacao.getDescricao(),
                avaliacao.getDataEntrega(),
                avaliacao.getProfessorId()
        }, Integer.class);
    }
}