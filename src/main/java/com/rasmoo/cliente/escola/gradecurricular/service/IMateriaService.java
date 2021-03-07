package com.rasmoo.cliente.escola.gradecurricular.service;

import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.entity.Materia;

import java.util.List;

public interface IMateriaService {

    List<MateriaDto> findAll();

    MateriaDto find(final Long id);

    Boolean save(final MateriaDto materiaDto);

    Boolean update(final MateriaDto materiaDto);

    Boolean delete(final Long id);
}
