package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.exception;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.exceptions.NegocioException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CustomExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(NegocioException.class)
    @ResponseBody
    public ResponseEntity<String> handleNegocioException(NegocioException ex) {
        logger.error("NegocioException: {}", ex.getMessage(), ex);
        String message = MensagemErroAplicacao.NEGOCIO_EXCEPTION.getMensagem() + ex.getMessage();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AplicacaoException.class)
    @ResponseBody
    public ResponseEntity<String> handleAplicacaoException(AplicacaoException ex) {
        logger.error("AplicacaoException: {}", ex.getMessage(), ex);
        String message = MensagemErroAplicacao.APLICACAO_EXCEPTION.getMensagem() + ex.getMessage();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<String> handleGenericException(Exception ex) {
        logger.error("Exception: {}", ex.getMessage(), ex);
        String message = MensagemErroAplicacao.GENERIC_ERROR.getMensagem();
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

