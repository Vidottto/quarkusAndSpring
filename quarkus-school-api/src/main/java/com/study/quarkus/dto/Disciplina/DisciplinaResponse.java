package com.study.quarkus.dto.Disciplina;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.study.quarkus.model.Curso;
import com.study.quarkus.model.Professor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class DisciplinaResponse {
    
    private int id;
    private String name;
    private Professor professor;
    private int creditos;
    private Curso curso;

}