package com.gym.config.handler;

import com.gym.config.handler.dto.ErroResponse;
import com.gym.config.handler.exception.RecursoNaoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> tratarErroDeValidacao(MethodArgumentNotValidException ex,
                                                                HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErroResponse.deValidacao(ex, request));
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> tratarRecursoNaoEncontrado(RecursoNaoEncontradoException ex,
                                                                     HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErroResponse.deRecursoNaoEncontrado(ex, request));
    }
}
