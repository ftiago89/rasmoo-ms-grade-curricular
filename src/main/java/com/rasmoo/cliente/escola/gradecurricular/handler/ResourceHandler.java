package com.rasmoo.cliente.escola.gradecurricular.handler;

import com.rasmoo.cliente.escola.gradecurricular.exception.CursoException;
import com.rasmoo.cliente.escola.gradecurricular.exception.MateriaException;
import com.rasmoo.cliente.escola.gradecurricular.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ResourceHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Map<String, String>>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException m) {
        Map<String, String> errors = new HashMap<>();
        Response<Map<String, String>> response = new Response<>();
        m.getAllErrors().forEach(error -> errors.put(((FieldError) error).getField(), error.getDefaultMessage()));

        response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
        response.setData(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MateriaException.class)
    public ResponseEntity<Response<String>> handlerMateriaException(MateriaException m) {
        Response<String> response = new Response<>();
        response.setHttpStatus(m.getHttpStatus().value());
        response.setData(m.getMessage());

        return ResponseEntity.status(m.getHttpStatus()).body(response);
    }

    @ExceptionHandler(CursoException.class)
    public ResponseEntity<Response<String>> handlerCursoException(CursoException m) {
        Response<String> response = new Response<>();
        response.setHttpStatus(m.getHttpStatus().value());
        response.setData(m.getMessage());

        return ResponseEntity.status(m.getHttpStatus()).body(response);
    }
}
