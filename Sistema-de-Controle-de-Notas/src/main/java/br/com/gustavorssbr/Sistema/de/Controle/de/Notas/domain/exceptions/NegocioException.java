package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.exceptions;
public class NegocioException extends RuntimeException {
    public NegocioException(String mensagem) {
        super(mensagem);
    }
}

