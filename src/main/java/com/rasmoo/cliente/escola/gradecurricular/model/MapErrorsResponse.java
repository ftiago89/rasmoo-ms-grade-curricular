package com.rasmoo.cliente.escola.gradecurricular.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class MapErrorsResponse {
    private final int httpStatus;
    private final Map<String, String> errors;
    private final Long timestamp;
}
