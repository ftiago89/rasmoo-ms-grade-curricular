package com.rasmoo.cliente.escola.gradecurricular.service;

import com.rasmoo.cliente.escola.gradecurricular.model.Materia;
import com.rasmoo.cliente.escola.gradecurricular.repository.IMateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaServiceImpl implements IMateriaService{

    @Autowired
    private IMateriaRepository materiaRepository;


    @Override
    public List<Materia> findAll() {
        return this.materiaRepository.findAll();
    }

    @Override
    public Materia find(Long id) {
        return this.materiaRepository.findById(id).get();
    }

    @Override
    public Boolean save(Materia materia) {
        this.materiaRepository.save(materia);
        return null;
    }

    @Override
    public Boolean update(Materia materia) {
        Materia updatedMateria = this.materiaRepository.findById(materia.getId()).get();

        updatedMateria.setCod(materia.getCod());
        updatedMateria.setFrequency(materia.getFrequency());
        updatedMateria.setHours(materia.getHours());
        updatedMateria.setName(materia.getName());

        this.materiaRepository.save(updatedMateria);
        return true;
    }

    @Override
    public Boolean delete(Long id) {
        this.materiaRepository.deleteById(id);
        return true;
    }
}
