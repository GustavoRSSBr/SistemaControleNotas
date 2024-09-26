package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.command;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.AlunoRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.LoginRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.adapter.input.usuario.dto.ProfessorRequestDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Usuario;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.MensagemErro;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.TipoUsuario;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.exceptions.NegocioException;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.seguranca.ISegurancaConfig;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.port.input.usuario.IUsuario;
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
    public String autenticar(LoginRequestDTO loginRequestDTO) {
        if (!usuarioRepository.existeUsuario(loginRequestDTO.getEmail())) {
            throw new NegocioException(MensagemErro.EMAIL_USUARIO_INVALIDO.getMensagem());
        }

        Usuario usuario = usuarioRepository.obterPorEmail(loginRequestDTO.getEmail());

        if (segurancaConfig.compararSenhaHash(loginRequestDTO.getSenha(), usuario.getSenha())) {
            return segurancaConfig.gerarToken(usuario);
        }

        throw new NegocioException(MensagemErro.SENHA_USUARIO_INCORRETA.getMensagem());

    }

    @Override
    public void cadastrarAluno(AlunoRequestDTO alunoRequestDTO) {
        if (usuarioRepository.existeUsuario(alunoRequestDTO.getEmail())) {
            throw new NegocioException(MensagemErro.EMAIL_JA_CADASTRADO.getMensagem());
        }

        String senhaHash = segurancaConfig.criptografarSenha(alunoRequestDTO.getSenha());
        Usuario aluno = new Usuario(
                alunoRequestDTO.getNome(),
                alunoRequestDTO.getEmail(),
                senhaHash,
                TipoUsuario.ALUNO
        );

        usuarioRepository.salvar(aluno);
    }

    @Override
    public void cadastrarProfessor(ProfessorRequestDTO professorRequestDTO) {
        if (usuarioRepository.existeUsuario(professorRequestDTO.getEmail())) {
            throw new NegocioException(MensagemErro.EMAIL_JA_CADASTRADO.getMensagem());
        }
        String senhaHash = segurancaConfig.criptografarSenha(professorRequestDTO.getSenha());
        Usuario professor = new Usuario(
                professorRequestDTO.getNome(),
                professorRequestDTO.getEmail(),
                senhaHash,
                TipoUsuario.PROFESSOR
        );

        usuarioRepository.salvar(professor);
    }
}
