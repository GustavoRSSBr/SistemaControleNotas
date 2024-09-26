package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.entities;


import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.MensagemErro;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.TipoUsuario;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.exceptions.NegocioException;

public class Usuario {
    private Integer idUsuario;
    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;

    public Usuario(String nome, String email, String senha, TipoUsuario tipoUsuario) {
        if (nome == null || nome.isEmpty()) {
            throw new NegocioException(MensagemErro.NOME_USUARIO_VAZIO.getMensagem());
        }

        if (email == null || !email.contains("@")) {
            throw new NegocioException(MensagemErro.EMAIL_USUARIO_INVALIDO.getMensagem());
        }

        if (senha == null || senha.length() < 6) {
            throw new NegocioException(MensagemErro.SENHA_USUARIO_CURTA.getMensagem());
        }

        if (tipoUsuario == null) {
            throw new NegocioException(MensagemErro.TIPO_USUARIO_OBRIGATORIO.getMensagem());
        }
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario(){}

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
