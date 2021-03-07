package com.rasmoo.cliente.escola.gradecurricular.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CursoDto {

    private Long id;

    private String name;

    private String cod;

    private List<Long> materias;
}
