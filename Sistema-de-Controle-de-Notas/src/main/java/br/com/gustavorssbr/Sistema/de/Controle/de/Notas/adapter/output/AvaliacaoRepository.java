package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.output;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Avaliacao;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Nota;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.output.avaliacao.IAvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class AvaliacaoRepository implements IAvaliacaoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer criarAvaliacao(Avaliacao avaliacao) {
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
        String sql = "SELECT EXISTE_ENTREGA(?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, idEntrega);
    }

    @Override
    public boolean validarRelacaoEntregaComProfessor(int idProfessor, Integer idEntrega) {
        String sql = "SELECT RELACAO_ENTREGA_PROFESSOR(?, ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, idProfessor, idEntrega);
    }

    @Override
    public Integer lancarNota(Nota nota) {
        String sql = "SELECT LANCAR_NOTA(?, ?, ?)";
        return jdbcTemplate.queryForObject(sql, Integer.class,
                nota.getIdEntrega(),
                nota.getNota(),
                nota.getFeedback()
        );
    }

    @Override
    public boolean existeNotaNaEntrega(int idEntrega) {
        String sql = "SELECT EXISTE_NOTA_NA_ENTREGA(?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, idEntrega);
    }


}
