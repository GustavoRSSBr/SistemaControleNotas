package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.output.entrega;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.entrega.dto.EntregaResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Entrega;

import java.time.LocalDateTime;
import java.util.List;

public interface IEntregaRepository {
    Integer criarEntrega(Entrega entrega);
    boolean existeAvaliacao(int idAvaliacao);
    boolean existeEntregaParaAvaliacao(int idAvaliacao, int idAluno);

    boolean validarTempoEntrega(LocalDateTime dataEntrega, int idAvaliavao);

    boolean verificarProfessorAvaliacao(int professorId, int avaliacaoId);

    List<EntregaResponseDTO> listarEntregasPorAvaliacao(int avaliacaoId);
}

