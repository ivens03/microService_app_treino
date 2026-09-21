package com.gym.config.handler.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gym.config.handler.exception.RecursoNaoEncontradoException;
import com.gym.config.log.CorrelationIdFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.List;

public record ErroResponse(
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss.SSS")
        LocalDateTime timestamp,
        int status,
        String erro,
        String mensagem,
        String path,
        String requestId,
        List<CampoErro> campos
) {

    public static ErroResponse deValidacao(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<CampoErro> campos = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new CampoErro(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        return criar(HttpStatus.BAD_REQUEST, "Erro de validação", "Um ou mais campos estão inválidos", request, campos);
    }

    public static ErroResponse deRecursoNaoEncontrado(RecursoNaoEncontradoException ex, HttpServletRequest request) {
        return criar(HttpStatus.NOT_FOUND, "Recurso não encontrado", ex.getMessage(), request, List.of());
    }

    private static ErroResponse criar(HttpStatus status, String erro, String mensagem,
                                       HttpServletRequest request, List<CampoErro> campos) {
        return new ErroResponse(
                LocalDateTime.now(),
                status.value(),
                erro,
                mensagem,
                request.getRequestURI(),
                MDC.get(CorrelationIdFilter.REQUEST_ID_MDC_KEY),
                campos);
    }
}
