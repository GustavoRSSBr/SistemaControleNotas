package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.service;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.AlunoDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.LoginDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.ProfessorDTO;


public interface IUsuario {
    String autenticar(LoginDTO loginDTO);
    Integer cadastrarAluno(AlunoDTO alunoDTO);
    Integer cadastrarProfessor(ProfessorDTO professorDTO);
}
