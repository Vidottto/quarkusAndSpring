package com.study.quarkus.dto.Disciplina;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class DisciplinaRequest {
    
    @NotBlank(message = "É necessário incluir um nome para a disciplina")
    private String name;

    private Integer professor;

    @Positive(message = "O número de créditos deve ser maior que 0")
    private int creditos;

    private int curso;

}
