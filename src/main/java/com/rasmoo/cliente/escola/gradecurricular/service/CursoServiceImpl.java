package com.rasmoo.cliente.escola.gradecurricular.service;

import com.rasmoo.cliente.escola.gradecurricular.constants.ConstMessages;
import com.rasmoo.cliente.escola.gradecurricular.dto.CursoDto;
import com.rasmoo.cliente.escola.gradecurricular.entity.Curso;
import com.rasmoo.cliente.escola.gradecurricular.entity.Materia;
import com.rasmoo.cliente.escola.gradecurricular.exception.CursoException;
import com.rasmoo.cliente.escola.gradecurricular.repository.ICursoRepository;
import com.rasmoo.cliente.escola.gradecurricular.repository.IMateriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoServiceImpl implements ICursoService {

    @Autowired
    private ICursoRepository cursoRepository;

    @Autowired
    private IMateriaRepository materiaRepository;


    @Override
    public List<Curso> findAll() {
        try {
            List<Curso> cursos = this.cursoRepository.findAll();
            if (!cursos.isEmpty()) {
                return cursos;
            }
            throw new CursoException(ConstMessages.ERRO_CURSO_NAO_ENCONTRADO.getValue(), HttpStatus.BAD_REQUEST);
        } catch (CursoException c) {
            throw c;
        } catch (Exception e) {
            throw new CursoException(ConstMessages.ERRO_GENERICO.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public CursoDto find(Long id) {
        try {
            Curso curso = this.cursoRepository.findById(id).orElseThrow(() ->
                    new CursoException(ConstMessages.ERRO_CURSO_NAO_ENCONTRADO.getValue(), HttpStatus.BAD_REQUEST));
            return this.toCursoDto(curso);
        } catch (CursoException c) {
            throw c;
        } catch (Exception e) {
            throw new CursoException(ConstMessages.ERRO_GENERICO.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean save(CursoDto cursoDto) {
        try {
            if (cursoDto.getId() == null){
                this.cursoRepository.save(this.fromDto(cursoDto));
                return true;
            }
            throw new CursoException(ConstMessages.ERRO_ID_INFORMADO.getValue(), HttpStatus.BAD_REQUEST);
        } catch (CursoException c) {
            throw c;
        } catch (Exception e) {
            throw new CursoException(ConstMessages.ERRO_GENERICO.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean update(CursoDto cursoDto) {
        try {
            this.find(cursoDto.getId());
            this.cursoRepository.save(this.fromDto(cursoDto));
            return true;
        } catch (CursoException c) {
            throw c;
        } catch (Exception e) {
            throw new CursoException(ConstMessages.ERRO_GENERICO.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean delete(Long id) {
        try {
            this.cursoRepository.delete(this.fromDto(this.find(id)));
            return true;
        } catch (CursoException c) {
            throw c;
        } catch (Exception e) {
            throw new CursoException(ConstMessages.ERRO_GENERICO.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private CursoDto toCursoDto(Curso curso) {
        CursoDto cursoDto = new CursoDto();
        cursoDto.setId(curso.getId());
        cursoDto.setCod(curso.getCod());
        cursoDto.setName(curso.getName());
        cursoDto.setMaterias(curso.getMaterias().stream().map(Materia::getId).collect(Collectors.toList()));
        return cursoDto;
    }

    private Curso fromDto(CursoDto cursoDto){
        Curso curso = new Curso();
        curso.setId(cursoDto.getId());
        curso.setCod(cursoDto.getCod());
        curso.setName(cursoDto.getName());

        cursoDto.getMaterias().forEach(id -> curso.getMaterias().add(this.materiaRepository.findById(id).orElseThrow(() ->
                new CursoException(ConstMessages.ERRO_MATERIA_NAO_ENCONTRADA.getValue(), HttpStatus.BAD_REQUEST))));

        return curso;
    }
}
