package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.services.impl;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.dtos.request.AlunoDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.dtos.request.LoginDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.dtos.request.ProfessorDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.enums.MensagemErroApplication;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.exceptions.ApplicationException;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.services.IUsuarioService;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities.Usuario;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.TipoUsuario;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.repositories.IUsuarioRepository;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.infrastructure.utils.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final IUsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public String autenticar(LoginDTO loginDTO) {
        Usuario usuario = usuarioRepository.obterPorEmail(loginDTO.getEmail());
        if (usuario != null && passwordEncoder.matches(loginDTO.getSenha(), usuario.getSenha())) {
            return JwtUtil.generateToken(usuario);
        }
        throw new ApplicationException(MensagemErroApplication.EMAIL_USUARIO_INVALIDO.getMensagem());
    }

    @Override
    public Integer cadastrarAluno(AlunoDTO alunoDTO) {
        if (usuarioRepository.obterPorEmail(alunoDTO.getEmail()) != null) {
            throw new ApplicationException(MensagemErroApplication.EMAIL_JA_CADASTRADO.getMensagem());
        }

        String senhaHash = passwordEncoder.encode(alunoDTO.getSenha());
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
            throw new ApplicationException(MensagemErroApplication.EMAIL_JA_CADASTRADO.getMensagem());
        }
        String senhaHash = passwordEncoder.encode(professorDTO.getSenha());
        Usuario professor = new Usuario(
                professorDTO.getNome(),
                professorDTO.getEmail(),
                senhaHash,
                TipoUsuario.PROFESSOR
        );

        return usuarioRepository.salvar(professor);
    }
}
