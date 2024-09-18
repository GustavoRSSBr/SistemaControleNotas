package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

import java.util.Locale;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class CustomErrorResponse implements ErrorResponse {

    private String mensagem;
    private String path;
    private Integer code;

    public CustomErrorResponse(String mensagem, String path, Integer code) {
        this.mensagem = mensagem;
        this.path = path;
        this.code = code;
    }

    @JsonIgnore
    @Override
    public HttpStatusCode getStatusCode() {
        return HttpStatusCode.valueOf(code); // Retorna o código de status HTTP
    }

    @JsonIgnore
    @Override
    public ProblemDetail getBody() {
        ProblemDetail problemDetail = ProblemDetail.forStatus(getStatusCode());
        problemDetail.setTitle(mensagem);
        problemDetail.setDetail(path);
        return problemDetail;
    }

    @JsonIgnore
    @Override
    public String getTypeMessageCode() {
        return ErrorResponse.super.getTypeMessageCode();
    }

    @JsonIgnore
    @Override
    public String getTitleMessageCode() {
        return ErrorResponse.super.getTitleMessageCode();
    }

    @JsonIgnore
    @Override
    public String getDetailMessageCode() {
        return ErrorResponse.super.getDetailMessageCode();
    }

    @JsonIgnore
    @Override
    public Object[] getDetailMessageArguments() {
        return ErrorResponse.super.getDetailMessageArguments();
    }

    @JsonIgnore
    @Override
    public Object[] getDetailMessageArguments(MessageSource messageSource, Locale locale) {
        return ErrorResponse.super.getDetailMessageArguments(messageSource, locale);
    }

    @JsonIgnore
    @Override
    public ProblemDetail updateAndGetBody(MessageSource messageSource, Locale locale) {
        ProblemDetail problemDetail = getBody();
        problemDetail.setTitle(getTitleMessageCode());
        problemDetail.setDetail(getDetailMessageCode());
        return problemDetail;
    }
}
