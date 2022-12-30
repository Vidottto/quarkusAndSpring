package com.study.quarkus.dto.Aluno;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
@Produces(MediaType.APPLICATION_JSON)
public class AlunoResponse {
    
    private int id;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String tutor;

}