package com.rasmoo.cliente.escola.gradecurricular.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_materia")
public class Materia implements Serializable{
    private static final long serialVersionUID = 4442799689174007915L;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(name = "name")
    private String name;

    @Column(name = "hrs")
    private int hours;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(name = "cod")
    private String cod;

    @Column(name = "freq")
    private int frequency;
}
