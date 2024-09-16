package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.infrastructure.config;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.TipoUsuario;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.dtos.response.CustomErrorResponse;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.application.dtos.request.JwtDTO;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.infrastructure.enums.ErrorMessages;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.infrastructure.utils.ErrorResponseFactory;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.infrastructure.utils.JwtUtil;
import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.infrastructure.utils.RotasUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class SistemaControleNotaFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();
        if (path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!checkPathExistence(request)) {
            response.sendError(HttpStatus.NOT_FOUND.value());
            return;
        }

        if (checkRoleExistence(request)) {
            if (!checkHeadersExistence(request)) {
                sendBadRequestResponse(response, request);
                return;
            } else {
                try {
                    String token = request.getHeader("Authorization").replace("Bearer ", "");
                    JwtDTO jwtDTO = JwtUtil.decodeToken(token);
                    TipoUsuario tipoUsuario = jwtDTO.getTipoUsuario();

                    if (!isRoleAuthorized(request, tipoUsuario)) {
                        sendForbiddenResponse(response, request);
                        return;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    sendInvalidTokenResponse(response, request);
                    return;
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean checkPathExistence(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        return RotasUtil.getRotas(resourceLoader).containsKey(servletPath);
    }

    private boolean checkHeadersExistence(HttpServletRequest request) {
        return request.getHeader("Authorization") != null && !request.getHeader("Authorization").isBlank();
    }

    private boolean checkRoleExistence(HttpServletRequest request) {
        List<TipoUsuario> roles = RotasUtil.getRotas(resourceLoader).get(request.getServletPath());

        if (roles == null || roles.isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean isRoleAuthorized(HttpServletRequest request, TipoUsuario tipoUsuario) {
        try {
            return RotasUtil.getRotas(resourceLoader).get(request.getServletPath()).contains(tipoUsuario);
        } catch (Exception e) {
            return false;
        }
    }

    private void sendBadRequestResponse(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        CustomErrorResponse errorResponse
                = ErrorResponseFactory
                .createResponseError(ErrorMessages.DESC_BAD_REQUEST_HEADERS.getMessage(), request.getServletPath(), HttpStatus.BAD_REQUEST.value());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

    private void sendForbiddenResponse(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        CustomErrorResponse errorResponse = ErrorResponseFactory
                .createResponseError(
                        ErrorMessages.DESC_ROLE_SEM_PERMISSAO.getMessage(),
                        request.getServletPath(),
                        HttpStatus.FORBIDDEN.value()
                );
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

    private void sendInvalidTokenResponse(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        CustomErrorResponse errorResponse
                = ErrorResponseFactory.createResponseError(
                ErrorMessages.DESC_TOKEN_INVALIDO.getMessage(), request.getServletPath(), HttpStatus.BAD_REQUEST.value());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}

