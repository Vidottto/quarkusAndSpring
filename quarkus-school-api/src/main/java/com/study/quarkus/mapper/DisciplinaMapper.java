package com.study.quarkus.mapper;

import com.study.quarkus.dto.Disciplina.DisciplinaRequest;
import com.study.quarkus.dto.Disciplina.DisciplinaResponse;
import com.study.quarkus.model.Disciplina;
import com.study.quarkus.repository.CursoRepository;
import com.study.quarkus.repository.ProfessorRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class DisciplinaMapper {

    @Inject
    ProfessorRepository professorRepository;

    @Inject
    CursoRepository cursoRepository;

    public List<DisciplinaResponse> toResponse(List<Disciplina> listOfDisciplinas) {

        if (Objects.isNull(listOfDisciplinas)) return new ArrayList<>();

        return listOfDisciplinas.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public DisciplinaResponse toResponse(Disciplina entity) {

        if (Objects.isNull(entity)) return null;

        return  DisciplinaResponse.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .professor(entity.getProfessor())
                    .creditos(entity.getCreditos())
                    .curso(entity.getCurso().getDescricao())
                    .build();
    }

    public Disciplina toEntity(DisciplinaRequest request) {
         if (Objects.isNull(request)) {
             return null;
         } else {
             return Disciplina.builder()
                     .name(request.getName())
                     .professor(professorRepository.findById(request.getProfessor()))
                     .creditos(request.getCreditos())
                     .curso(cursoRepository.findById(request.getCurso()))
                     .build();
         }
    }
}
