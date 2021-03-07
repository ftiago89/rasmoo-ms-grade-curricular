package com.rasmoo.cliente.escola.gradecurricular.service;

import com.rasmoo.cliente.escola.gradecurricular.model.Materia;

import java.util.List;

public interface IMateriaService {

    public List<Materia> findAll();

    public Materia find(final Long id);

    public Boolean save(final Materia materia);

    public Boolean update(final Materia materia);

    public Boolean delete(final Long id);
}
