package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.utils;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.MensagemErro;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.exceptions.NegocioException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataUtil {

    private static final String DATE_PATTERN = "dd/MM/yyyy HH:mm";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static LocalDateTime converteStringParaData(String data) {
        try {
            return LocalDateTime.parse(data, formatter);
        } catch (DateTimeParseException e) {
            throw new NegocioException(MensagemErro.DATA_INVALIDA.getMensagem() + data);
        }
    }
}

