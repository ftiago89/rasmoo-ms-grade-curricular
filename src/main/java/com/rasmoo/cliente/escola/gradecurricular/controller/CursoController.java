package com.rasmoo.cliente.escola.gradecurricular.controller;

import com.rasmoo.cliente.escola.gradecurricular.dto.CursoDto;
import com.rasmoo.cliente.escola.gradecurricular.entity.Curso;
import com.rasmoo.cliente.escola.gradecurricular.model.Response;
import com.rasmoo.cliente.escola.gradecurricular.service.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cursos")
public class CursoController {

    @Autowired
    private ICursoService cursoService;

    @GetMapping
    public ResponseEntity<Response<List<Curso>>> findAll(){
        Response<List<Curso>> response = new Response<>();
        response.setData(this.cursoService.findAll());
        response.setHttpStatus(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<CursoDto>> find(@PathVariable Long id){
        Response<CursoDto> response = new Response<>();
        response.setData(this.cursoService.find(id));
        response.setHttpStatus(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<Response<Boolean>> save(@RequestBody CursoDto cursoDto){
        Response<Boolean> response = new Response<>();
        response.setData(this.cursoService.save(cursoDto));
        response.setHttpStatus(HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<Response<Boolean>> update(@RequestBody CursoDto cursoDto){
        Response<Boolean> response = new Response<>();
        response.setData(this.cursoService.update(cursoDto));
        response.setHttpStatus(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> delete(@PathVariable Long id){
        Response<Boolean> response = new Response<>();
        response.setData(this.cursoService.delete(id));
        response.setHttpStatus(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
