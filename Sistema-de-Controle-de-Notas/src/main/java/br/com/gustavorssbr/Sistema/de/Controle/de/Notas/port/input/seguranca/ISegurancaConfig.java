package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.seguranca;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Usuario;

public interface ISegurancaConfig {
    String criptografarSenha(String senha);
    boolean compararSenhaHash(String senha, String senhaHash);
    int buscarIdToken(String token);
    String gerarToken(Usuario usuario);
}