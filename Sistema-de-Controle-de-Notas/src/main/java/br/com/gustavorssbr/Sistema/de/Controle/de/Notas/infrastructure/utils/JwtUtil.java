package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.infrastructure.utils;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Usuario;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.TipoUsuario;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.dtos.request.JwtDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);;

    public static String generateToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .claim("id", usuario.getIdUsuario())
                .claim("email", usuario.getEmail())
                .claim("tipoUsuario", usuario.getTipoUsuario().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static JwtDTO decodeToken(String token) {
        Jws<Claims> jws = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token);
        Claims claims = jws.getBody();

        JwtDTO jwtDTO = new JwtDTO();
        jwtDTO.setId(claims.get("id", Integer.class));
        jwtDTO.setEmail(claims.get("email", String.class));
        jwtDTO.setTipoUsuario(TipoUsuario.valueOf(claims.get("tipoUsuario", String.class)));

        return jwtDTO;
    }
}
