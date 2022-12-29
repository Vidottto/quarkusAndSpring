package com.study.quarkus.dto.Aluno;

import javax.validation.constraints.NotBlank;

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
public class AlunoRequest {
    
    @NotBlank(message = "O nome do aluno deve constar no cadastro")
    private String nome;
    
    private int tutorId;

}
