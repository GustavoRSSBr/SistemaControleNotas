package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.output;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Usuario;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.TipoUsuario;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.output.usuario.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository implements IUsuarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Usuario> usuarioRowMapper = (rs, rowNum) -> {
        Usuario usuario = new Usuario(
                rs.getString("NOME_USUARIO"),
                rs.getString("EMAIL_USUARIO"),
                rs.getString("SENHA_HASH_USUARIO"),
                TipoUsuario.valueOf(rs.getString("TIPO_USUARIO").toUpperCase())
        );
        usuario.setIdUsuario(rs.getInt("ID_USUARIO"));
        return usuario;
    };

    @Override
    public Usuario obterPorEmail(String email) {
        String sql = "SELECT * FROM OBTER_USUARIO_POR_EMAIL(?)";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{email}, usuarioRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Integer salvar(Usuario usuario) {
        String sql = "SELECT CRIAR_USUARIO(?, ?, ?, ?)";

        return jdbcTemplate.queryForObject(sql, new Object[]{
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getTipoUsuario().toString()
        }, Integer.class);
    }
}
