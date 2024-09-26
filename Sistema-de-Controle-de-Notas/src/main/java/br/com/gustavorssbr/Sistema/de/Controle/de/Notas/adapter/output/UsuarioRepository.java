package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.output;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Usuario;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.TipoUsuario;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.output.usuario.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository implements IUsuarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Usuario obterPorEmail(String email) {
        String sql = "SELECT * FROM OBTER_USUARIO_POR_EMAIL(?)";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Usuario.class), email);
    }

    @Override
    public boolean existeUsuario(String email) {
        String sql = "SELECT EXISTE_USUARIO(?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, email);
    }


    @Override
    public void salvar(Usuario usuario) {
        String sql = "CALL CRIAR_USUARIO(?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getTipoUsuario().toString()
        );
    }
}
