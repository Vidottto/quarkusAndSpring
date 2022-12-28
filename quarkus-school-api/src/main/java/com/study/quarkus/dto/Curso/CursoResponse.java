package com.study.quarkus.dto.Curso;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.study.quarkus.model.Disciplina;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class CursoResponse {
    
    private int id;
    private String descricao;
    private int duracao;
    private List<String> disciplinas;

}
