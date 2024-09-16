package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.infrastructure.handler;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.exceptions.ApplicationException;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.exceptions.NegocioException;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.infrastructure.enums.ErrorMessages;
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
        String message = ErrorMessages.NEGOCIO_EXCEPTION.getMessage() + ex.getMessage();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApplicationException.class)
    @ResponseBody
    public ResponseEntity<String> handleApplicationException(ApplicationException ex) {
        logger.error("ApplicationException: {}", ex.getMessage(), ex);
        String message = ErrorMessages.APPLICATION_EXCEPTION.getMessage() + ex.getMessage();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<String> handleGenericException(Exception ex) {
        logger.error("Exception: {}", ex.getMessage(), ex);
        String message = ErrorMessages.GENERIC_ERROR.getMessage();
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

