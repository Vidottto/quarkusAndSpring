package com.study.quarkus.dto.Professor;

import java.util.List;

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
public class ProfessorRequest {
    
    @NotBlank(message = "O nome do professor deve constar no cadastro")
    private String name;

    private List<Integer> idTutorados;

}
