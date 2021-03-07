package com.rasmoo.cliente.escola.gradecurricular.controller;

import com.rasmoo.cliente.escola.gradecurricular.model.Materia;
import com.rasmoo.cliente.escola.gradecurricular.service.IMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/materia")
public class MateriaController {

    @Autowired
    private IMateriaService materiaService;

    @GetMapping
    public ResponseEntity<List<Materia>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materia> find(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.find(id));
    }

    @PostMapping
    public ResponseEntity<Boolean> save(@RequestBody Materia materia){

        return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.save(materia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@RequestBody Materia materia){
        return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.update(materia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.delete(id));
    }
}
