package com.study.quarkus.dto.Professor;

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
public class ProfessorResponse {
    
    private int id;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> tutorados;

}