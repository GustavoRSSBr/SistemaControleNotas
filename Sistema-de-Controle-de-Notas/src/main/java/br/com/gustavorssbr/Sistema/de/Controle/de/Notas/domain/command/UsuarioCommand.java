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
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UsuarioCommand implements IUsuario {

    private final IUsuarioRepository usuarioRepository;
    private final ISegurancaConfig segurancaConfig;
    private static final Logger LOGGER = LoggerFactory.getLogger(AvaliacaoCommand.class);

    public UsuarioCommand(IUsuarioRepository usuarioRepository, ISegurancaConfig segurancaConfig) {
        this.usuarioRepository = usuarioRepository;
        this.segurancaConfig = segurancaConfig;
    }

    @Override
    public String autenticar(LoginRequestDTO loginRequestDTO) {
        LOGGER.info("Início do método autenticar com request de dados de login");

        LOGGER.info("Verificando se existe um usuário pelo email");
        if (!usuarioRepository.existeUsuario(loginRequestDTO.getEmail())) {
            throw new NegocioException(MensagemErro.EMAIL_USUARIO_INVALIDO.getMensagem());
        }

        LOGGER.info("Buscando usuario na base de dados");
        Usuario usuario = usuarioRepository.obterPorEmail(loginRequestDTO.getEmail());

        LOGGER.info("Autenticando o usuario");
        if (segurancaConfig.compararSenhaHash(loginRequestDTO.getSenha(), usuario.getSenha())) {
            return segurancaConfig.gerarToken(usuario);
        }

        throw new NegocioException(MensagemErro.SENHA_USUARIO_INCORRETA.getMensagem());

    }

    @Override
    public void cadastrarAluno(AlunoRequestDTO alunoRequestDTO) {
        LOGGER.info("Início do método cadastrarAluno com request de dados do aluno");

        LOGGER.info("Verificando se existe um usuário pelo email");
        if (usuarioRepository.existeUsuario(alunoRequestDTO.getEmail())) {
            throw new NegocioException(MensagemErro.EMAIL_JA_CADASTRADO.getMensagem());
        }

        LOGGER.info("Validando informações do request");
        validarInformacoesAluno(alunoRequestDTO);

        LOGGER.info("Criptografando dados do usuario");
        String senhaHash = segurancaConfig.criptografarSenha(alunoRequestDTO.getSenha());

        LOGGER.info("Construindo usuario");
        Usuario aluno = Usuario.builder()
                .nome(alunoRequestDTO.getNome())
                .email(alunoRequestDTO.getEmail())
                .senha(senhaHash)
                .tipoUsuario(TipoUsuario.ALUNO)
                .build();

        usuarioRepository.salvar(aluno);
    }

    private void validarInformacoesAluno(AlunoRequestDTO alunoRequestDTO) {
        if (alunoRequestDTO.getNome() == null || alunoRequestDTO.getNome().isEmpty()) {
            throw new NegocioException(MensagemErro.NOME_USUARIO_VAZIO.getMensagem());
        }

        if (alunoRequestDTO.getEmail() == null || !alunoRequestDTO.getEmail().contains("@")) {
            throw new NegocioException(MensagemErro.EMAIL_USUARIO_INVALIDO.getMensagem());
        }

        if (alunoRequestDTO.getSenha() == null || alunoRequestDTO.getSenha().length() < 6) {
            throw new NegocioException(MensagemErro.SENHA_USUARIO_CURTA.getMensagem());
        }
    }

    @Override
    public void cadastrarProfessor(ProfessorRequestDTO professorRequestDTO) {
        LOGGER.info("Início do método cadastrarProfessor com request de dados do professor");

        LOGGER.info("Verificando se existe um usuário pelo email");
        if (usuarioRepository.existeUsuario(professorRequestDTO.getEmail())) {
            throw new NegocioException(MensagemErro.EMAIL_JA_CADASTRADO.getMensagem());
        }

        LOGGER.info("Validando informações do request");
        validarInformacoesProfessor(professorRequestDTO);

        LOGGER.info("Criptografando dados do usuario");
        String senhaHash = segurancaConfig.criptografarSenha(professorRequestDTO.getSenha());

        LOGGER.info("Construindo usuario");
        Usuario professor = Usuario.builder()
                .nome(professorRequestDTO.getNome())
                .email(professorRequestDTO.getEmail())
                .senha(senhaHash)
                .tipoUsuario(TipoUsuario.ALUNO)
                .build();

        usuarioRepository.salvar(professor);
    }

    private void validarInformacoesProfessor(ProfessorRequestDTO professorRequestDTO) {
        if (professorRequestDTO.getNome() == null || professorRequestDTO.getNome().isEmpty()) {
            throw new NegocioException(MensagemErro.NOME_USUARIO_VAZIO.getMensagem());
        }

        if (professorRequestDTO.getEmail() == null || !professorRequestDTO.getEmail().contains("@")) {
            throw new NegocioException(MensagemErro.EMAIL_USUARIO_INVALIDO.getMensagem());
        }

        if (professorRequestDTO.getSenha() == null || professorRequestDTO.getSenha().length() < 8) {
            throw new NegocioException(MensagemErro.SENHA_USUARIO_CURTA.getMensagem());
        }
    }
}
