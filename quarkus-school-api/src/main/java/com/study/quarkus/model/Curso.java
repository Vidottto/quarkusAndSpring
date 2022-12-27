package com.study.quarkus.model;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.ManyToOne;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CURSO")
public class Curso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curso_id", nullable = false)
    private Integer id;

    @Column(name = "curso_descricao", nullable = false)
    private String descricao;

    @Column(name = "curso_duracao", nullable = false)
    private int duracao;

    // @OneToMany
    // @JoinColumn(name = "curso_disciplinas")
    // private List<DisciplinaModel> disciplinas;

    
}
