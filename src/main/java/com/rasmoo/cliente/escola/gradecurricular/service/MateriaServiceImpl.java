package com.rasmoo.cliente.escola.gradecurricular.service;

import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.entity.Materia;
import com.rasmoo.cliente.escola.gradecurricular.exception.MateriaException;
import com.rasmoo.cliente.escola.gradecurricular.repository.IMateriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@CacheConfig(cacheNames = "materia")
@Service
public class MateriaServiceImpl implements IMateriaService {

    private static final String GENERIC_ERROR = "Erro inesperado, contate o suporte.";
    private static final String MATERIA_NOT_FOUND = "Matéria não encontrada!";
    private final IMateriaRepository materiaRepository;
    private final ModelMapper mapper;

    @Autowired
    public MateriaServiceImpl(IMateriaRepository materiaRepository){
        this.mapper = new ModelMapper();
        this.materiaRepository = materiaRepository;
    }

    @CachePut(unless = "#result.size() < 3")
    @Override
    public List<MateriaDto> findAll() {
        try {
            List<Materia> materias = this.materiaRepository.findAll();

            return materias.stream().map(m -> this.mapper.map(m, MateriaDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new MateriaException(MATERIA_NOT_FOUND, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CachePut(key = "#id")
    @Override
    public MateriaDto find(Long id) {
        try {
            Materia materia = this.materiaRepository.findById(id).orElseThrow(() ->
                    new MateriaException(MATERIA_NOT_FOUND, HttpStatus.NOT_FOUND));
            return this.mapper.map(materia, MateriaDto.class);
        } catch (MateriaException m) {
            throw m;
        } catch (Exception e) {
            throw new MateriaException(GENERIC_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean save(MateriaDto materiaDto) {
        try {
            this.materiaRepository.save(this.mapper.map(materiaDto, Materia.class));
            return true;
        } catch (Exception e) {
            throw new MateriaException(GENERIC_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean update(MateriaDto materiaDto) {
        this.find(materiaDto.getId());
        try {
            this.materiaRepository.save(this.mapper.map(materiaDto, Materia.class));

            return true;
        } catch (Exception e) {
            throw new MateriaException(GENERIC_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean delete(Long id) {
        this.find(id);
        try {
            this.materiaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new MateriaException(GENERIC_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
