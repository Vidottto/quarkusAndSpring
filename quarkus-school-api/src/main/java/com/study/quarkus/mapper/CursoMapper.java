package com.study.quarkus.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import com.study.quarkus.dto.Curso.CursoRequest;
import com.study.quarkus.dto.Curso.CursoResponse;
import com.study.quarkus.dto.Curso.CursoResponse.CursoResponseBuilder;
import com.study.quarkus.model.Curso;

@ApplicationScoped
public class CursoMapper {
    public List<CursoResponse> toResponse(List<Curso> listOfCursos) {

        if (Objects.isNull(listOfCursos)) return new ArrayList<>();

        return listOfCursos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public CursoResponse toResponse(Curso entity) {

        if (Objects.isNull(entity)) return null;

        CursoResponseBuilder cursoResponse = CursoResponse.builder()
                                                    .id(entity.getId())
                                                    .descricao(entity.getDescricao())
                                                    .duracao(entity.getDuracao());

        if(!Objects.isNull(entity.getDisciplinas())) {
            cursoResponse.disciplinas(entity.getDisciplinas().stream()
                                            .map(d -> d.getName())
                                            .collect(Collectors.toList()));
        }

        return cursoResponse.build();
        
    }

    public Curso toEntity(CursoRequest request) {
         if (Objects.isNull(request)) {
             return null;
         } else {
             return Curso.builder()
                     .descricao(request.getDescricao())
                     .duracao(request.getDuracao())
                     .build();
         }
    }
}
