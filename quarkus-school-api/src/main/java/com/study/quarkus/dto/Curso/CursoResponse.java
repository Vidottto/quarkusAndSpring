package com.study.quarkus.dto.Curso;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int duracao;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> disciplinas;

}
