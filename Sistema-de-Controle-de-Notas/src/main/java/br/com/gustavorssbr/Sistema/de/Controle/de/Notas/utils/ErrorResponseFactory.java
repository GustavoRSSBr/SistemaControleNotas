package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.utils;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.dto.CustomErrorResponse;

public class ErrorResponseFactory {

    public static CustomErrorResponse createResponseError(String message, String path, Integer httpStatus) {
        return new CustomErrorResponse(message, path, httpStatus);
    }
}
