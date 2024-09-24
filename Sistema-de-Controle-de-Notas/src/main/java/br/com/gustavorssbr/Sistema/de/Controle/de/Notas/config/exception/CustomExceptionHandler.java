package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.exception;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.dto.ErrorResponseDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.MensagemErro;
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
    public ResponseEntity<ErrorResponseDTO> handleNegocioException(NegocioException ex) {
        logger.error("NegocioException: {}", ex.getMessage(), ex);
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AplicacaoException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseDTO> handleAplicacaoException(AplicacaoException ex) {
        logger.error("AplicacaoException: {}", ex.getMessage(), ex);
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex) {
        logger.error("Exception: {}", ex.getMessage(), ex);
        String messagem = MensagemErro.GENERIC_ERROR.getMensagem();
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(messagem);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

