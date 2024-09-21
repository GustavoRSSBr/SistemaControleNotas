package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.usuario;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.AlunoRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.LoginRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.ProfessorRequestDTO;


public interface IUsuario {
    String autenticar(LoginRequestDTO loginRequestDTO);
    void cadastrarAluno(AlunoRequestDTO alunoRequestDTO);
    void cadastrarProfessor(ProfessorRequestDTO professorRequestDTO);
}
