package com.rasmoo.cliente.escola.gradecurricular.repository;

import com.rasmoo.cliente.escola.gradecurricular.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICursoRepository extends JpaRepository<Curso, Long> {
}
