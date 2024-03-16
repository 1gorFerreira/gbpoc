package com.tauan.txbank.core.handle;

import com.tauan.txbank.core.handle.exceptions.RecursoNaoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.OffsetDateTime;
import java.util.List;

@ControllerAdvice
public class ApiExeptionHandler{

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Problem> handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex, WebRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;

        Problem problem = Problem.builder()
                .status(status.value())
                .title("Recurso nao encontrado")
                .timestamp(OffsetDateTime.now())
                .detail(ex.getMessage())
                .userMessage(ex.getMessage())
                .build();


        return ResponseEntity.status(status).body(problem);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Problem> validation(MethodArgumentNotValidException ex, HttpServletRequest request) {

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        List<Problem.ValidationError> validationErrors = ex.getBindingResult().getAllErrors().stream()
                .map(objectError -> {

                    String message = objectError.getDefaultMessage();

                    String name = objectError.getObjectName();

                    name = ((FieldError) objectError).getField();

                    return Problem.ValidationError.builder()
                            .field(name)
                            .userMessage(message)
                            .build();
                })
                .toList();

        Problem problem = Problem.builder()
                .status(status.value())
                .title("Erro validacao")
                .timestamp(OffsetDateTime.now())
                .detail(ex.getMessage())
                .userMessage("Erro em campos de validacao")
                .validationErrors(validationErrors)
                .build();

        return ResponseEntity.status(status).body(problem);
    }

}
