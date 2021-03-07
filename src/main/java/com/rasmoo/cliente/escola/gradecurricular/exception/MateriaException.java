package com.rasmoo.cliente.escola.gradecurricular.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MateriaException extends RuntimeException {
    private static final long serialVersionUID = 2205173443343180771L;

    private final HttpStatus httpStatus;

    public MateriaException(final String message, final HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }
}
