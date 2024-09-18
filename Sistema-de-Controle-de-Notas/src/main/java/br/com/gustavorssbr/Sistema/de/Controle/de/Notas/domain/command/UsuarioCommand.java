package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.command;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.AlunoDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.LoginDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.ProfessorDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.MensagemErro;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.exceptions.NegocioException;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.service.IUsuario;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Usuario;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.TipoUsuario;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.seguranca.ISegurancaConfig;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.output.usuario.IUsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioCommand implements IUsuario {

    private final IUsuarioRepository usuarioRepository;
    private final ISegurancaConfig segurancaConfig;

    public UsuarioCommand(IUsuarioRepository usuarioRepository, ISegurancaConfig segurancaConfig) {
        this.usuarioRepository = usuarioRepository;
        this.segurancaConfig = segurancaConfig;
    }

    @Override
    public String autenticar(LoginDTO loginDTO) {
        Usuario usuario = usuarioRepository.obterPorEmail(loginDTO.getEmail());
        if (usuario != null && segurancaConfig.compararSenhaHash(loginDTO.getSenha(), usuario.getSenha())) {
            return segurancaConfig.gerarToken(usuario);
        }
        throw new NegocioException(MensagemErro.EMAIL_USUARIO_INVALIDO.getMensagem());
    }

    @Override
    public Integer cadastrarAluno(AlunoDTO alunoDTO) {
        if (usuarioRepository.obterPorEmail(alunoDTO.getEmail()) != null) {
            throw new NegocioException(MensagemErro.EMAIL_JA_CADASTRADO.getMensagem());
        }

        String senhaHash = segurancaConfig.criptografarSenha(alunoDTO.getSenha());
        Usuario aluno = new Usuario(
                alunoDTO.getNome(),
                alunoDTO.getEmail(),
                senhaHash,
                TipoUsuario.ALUNO
        );

        return usuarioRepository.salvar(aluno);
    }

    @Override
    public Integer cadastrarProfessor(ProfessorDTO professorDTO) {
        if (usuarioRepository.obterPorEmail(professorDTO.getEmail()) != null) {
            throw new NegocioException(MensagemErro.EMAIL_JA_CADASTRADO.getMensagem());
        }
        String senhaHash = segurancaConfig.criptografarSenha(professorDTO.getSenha());
        Usuario professor = new Usuario(
                professorDTO.getNome(),
                professorDTO.getEmail(),
                senhaHash,
                TipoUsuario.PROFESSOR
        );

        return usuarioRepository.salvar(professor);
    }
}
