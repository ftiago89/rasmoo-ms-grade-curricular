package com.rasmoo.cliente.escola.gradecurricular.handler;

import com.rasmoo.cliente.escola.gradecurricular.exception.MateriaException;
import com.rasmoo.cliente.escola.gradecurricular.model.ErrorResponse;
import com.rasmoo.cliente.escola.gradecurricular.model.MapErrorsResponse;
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
    public ResponseEntity<MapErrorsResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException m){
        Map<String, String> errors = new HashMap<>();
        m.getAllErrors().forEach(error -> errors.put(((FieldError)error).getField(), error.getDefaultMessage()));
        MapErrorsResponse.MapErrorsResponseBuilder mapErrorsResponse = MapErrorsResponse.builder();
        mapErrorsResponse.errors(errors).httpStatus(HttpStatus.BAD_REQUEST.value())
                .timestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapErrorsResponse.build());
    }

    @ExceptionHandler(MateriaException.class)
    public ResponseEntity<ErrorResponse> handlerMateriaException(MateriaException m){
        ErrorResponse.ErrorResponseBuilder error = ErrorResponse.builder();
        error.message(m.getMessage());
        error.httpStatus(m.getHttpStatus().value());
        error.timestamp(System.currentTimeMillis());

        return ResponseEntity.status(m.getHttpStatus()).body(error.build());
    }
}
