package com.rasmoo.cliente.escola.gradecurricular.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_curso")
public class Curso implements Serializable {
    private static final long serialVersionUID = -5643831296339806643L;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(name = "name")
    private String name;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(name = "cod")
    private String cod;

    @ManyToMany
    @JoinColumn(name = "materia_id")
    private List<Materia> materias = new ArrayList<>();
}
