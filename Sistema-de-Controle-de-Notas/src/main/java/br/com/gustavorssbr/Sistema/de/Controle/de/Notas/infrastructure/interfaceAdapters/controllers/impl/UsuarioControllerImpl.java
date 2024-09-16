package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.infrastructure.interfaceAdapters.controllers.impl;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.dtos.request.AlunoDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.dtos.request.LoginDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.dtos.request.ProfessorDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.services.IUsuarioService;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.infrastructure.interfaceAdapters.controllers.IUsuarioController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scn")
public class UsuarioControllerImpl implements IUsuarioController {
    @Autowired
    private IUsuarioService usuarioService;
    @Override
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        String token = usuarioService.autenticar(loginDTO);
        return ResponseEntity.ok(token);
    }

    @Override
    @PostMapping("/professores/cadastrar-professor")
    public ResponseEntity<Integer> cadastrarProfessor(@RequestBody ProfessorDTO professorDTO) {
        Integer idProfessor = usuarioService.cadastrarProfessor(professorDTO);
        return ResponseEntity.ok(idProfessor);
    }

    @Override
    @PostMapping("/alunos/cadastrar-aluno")
    public ResponseEntity<Integer> cadastrarAluno(@RequestBody AlunoDTO alunoDTO) {
        Integer idAluno = usuarioService.cadastrarAluno(alunoDTO);
        return ResponseEntity.ok(idAluno);
    }
}
