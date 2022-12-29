package com.study.quarkus.dto.Curso;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties
public class CursoRequest {

    @NotBlank(message = "O nome do curso deve constar no cadastro")
    private String descricao;

    @Positive(message = "A duração do curso deve ser maior que 0 horas")
    private int duracao;

}
