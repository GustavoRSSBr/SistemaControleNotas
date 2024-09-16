package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.repositories;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Usuario;

public interface IUsuarioRepository {
    Usuario obterPorEmail(String email);
    Integer salvar(Usuario usuario);
}

