package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto.AtualizaNotaRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto.AvaliacaoRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.avaliacao.dto.NotaRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega.dto.EntregaRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.AlunoRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.LoginRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.ProfessorRequestDTO;

public class BaseTest {

    protected AvaliacaoRequestDTO criarAvaliacaoRequestDTO(){
        return AvaliacaoRequestDTO.builder()
                .titulo("Título da Avaliação")
                .descricao("Descrição detalhada da avaliação")
                .dataEntrega("10/12/2024 14:48")
                .build();
    }

    protected NotaRequestDTO criarNotaRequestDTO(){
        return NotaRequestDTO.builder()
                .idEntrega(1)
                .valorNota(8.0)
                .feedback("Muito bom")
                .build();
    }


    protected AtualizaNotaRequestDTO criarAtualizaNotaRequestDTO() {
        return AtualizaNotaRequestDTO.builder()
                .idNota(1)
                .valorNota(10.0)
                .feedback("Muito bem")
                .build();
    }

    protected EntregaRequestDTO criarEntregaRequestDTO(){

        return EntregaRequestDTO.builder()
                .conteudo("Conteudo teste")
                .idAvaliacao(1)
                .build();
    }

    protected LoginRequestDTO criarLoginRequestDTO(){
        return LoginRequestDTO.builder()
                .email("adm@adm.com")
                .senha("SenhaSegura@123")
                .build();
    }

    protected ProfessorRequestDTO criarProfessorRequestDTO(){
        return ProfessorRequestDTO.builder()
                .email("professor@professor.com.br")
                .nome("Carlos")
                .senha("P*rofessor123")
                .build();
    }

    protected AlunoRequestDTO cadatrarAlunoRequestDTO() {
        return AlunoRequestDTO.builder()
                .email("aluno@aluno.com.br")
                .nome("Augusto")
                .senha("A*luno#123")
                .build();
    }
}
