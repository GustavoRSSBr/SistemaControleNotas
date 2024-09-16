package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.services;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.dtos.request.AlunoDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.dtos.request.LoginDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.dtos.request.ProfessorDTO;

public interface IUsuarioService {
    String autenticar(LoginDTO loginDTO);
    Integer cadastrarAluno(AlunoDTO alunoDTO);
    Integer cadastrarProfessor(ProfessorDTO professorDTO);
}
