package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.seguranca;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Usuario;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.seguranca.ISegurancaConfig;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.utils.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SegurancaConfig implements ISegurancaConfig {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String criptografarSenha(String senha) {
        return passwordEncoder.encode(senha);
    }

    @Override
    public boolean compararSenhaHash(String senha, String senhaHash) {
        return passwordEncoder.matches(senha, senhaHash);
    }

    @Override
    public int buscarIdToken(String token) {
        return JwtUtil.decodeToken(token.replace("Bearer ", "")).getId();
    }

    @Override
    public String gerarToken(Usuario usuario) {
        return JwtUtil.generateToken(usuario);
    }
}
