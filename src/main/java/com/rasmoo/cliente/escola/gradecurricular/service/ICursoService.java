package com.rasmoo.cliente.escola.gradecurricular.service;

import com.rasmoo.cliente.escola.gradecurricular.dto.CursoDto;
import com.rasmoo.cliente.escola.gradecurricular.entity.Curso;

import java.util.List;

public interface ICursoService {

    List<Curso> findAll();

    CursoDto find(Long id);

    Boolean save(CursoDto cursoDto);

    Boolean update(CursoDto cursoDto);

    Boolean delete(Long id);
}
