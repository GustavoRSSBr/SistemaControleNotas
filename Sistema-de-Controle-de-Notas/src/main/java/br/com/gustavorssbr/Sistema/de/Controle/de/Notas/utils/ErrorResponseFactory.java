package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.utils;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.dto.ErrorAutenticacaoResponseDTO;

public class ErrorResponseFactory {

    public static ErrorAutenticacaoResponseDTO createResponseError(String message, String path, Integer httpStatus) {
        return new ErrorAutenticacaoResponseDTO(message, path, httpStatus);
    }
}
