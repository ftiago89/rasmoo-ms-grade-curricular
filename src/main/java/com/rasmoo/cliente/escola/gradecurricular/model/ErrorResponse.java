package com.rasmoo.cliente.escola.gradecurricular.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {
    private final String message;
    private final int httpStatus;
    private final long timestamp;
}
