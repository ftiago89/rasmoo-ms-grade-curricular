package com.rasmoo.cliente.escola.gradecurricular.controller;

import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.service.IMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/materia")
public class MateriaController {

    @Autowired
    private IMateriaService materiaService;

    @GetMapping
    public ResponseEntity<List<MateriaDto>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaDto> find(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.find(id));
    }

    @PostMapping
    public ResponseEntity<Boolean> save(@Valid @RequestBody MateriaDto materiaDto){
        return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.save(materiaDto));
    }

    @PutMapping
    public ResponseEntity<Boolean> update(@Valid @RequestBody MateriaDto materiaDto){
        return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.update(materiaDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.delete(id));
    }
}
