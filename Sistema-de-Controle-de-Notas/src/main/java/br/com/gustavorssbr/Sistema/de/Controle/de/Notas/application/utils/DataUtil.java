package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.utils;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.enums.MensagemErroApplication;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.exceptions.ApplicationException;

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
            throw new ApplicationException(MensagemErroApplication.DATA_INVALIDA.getMensagem() + data);
        }
    }
}

