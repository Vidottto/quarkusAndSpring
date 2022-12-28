package com.study.quarkus.mapper;

import com.study.quarkus.dto.Professor.ProfessorRequest;
import com.study.quarkus.dto.Professor.ProfessorResponse;
import com.study.quarkus.model.Professor;
import com.study.quarkus.repository.AlunoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProfessorMapper {

    @Inject
    AlunoRepository alunoRepository;

    public List<ProfessorResponse> toResponse(List<Professor> listOfProfessors) {

        if (Objects.isNull(listOfProfessors)) return new ArrayList<>();

        return listOfProfessors.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ProfessorResponse toResponse(Professor entity) {

        if (Objects.isNull(entity)) return null;

        if (!Objects.isNull(entity.getTutorados())) {
            return  ProfessorResponse.builder()
                    .id(entity.getId())
                    .tutorados(entity.getTutorados().stream()
                        .map(t -> t.getNome())
                        .collect(Collectors.toList()))
                    .name(entity.getName())
                    .build();
        } else {
        return  ProfessorResponse.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .build();
        }
    }

    public Professor toEntity(ProfessorRequest request) {
         if (Objects.isNull(request)) {
             return null;
         } else {
            if (Objects.isNull(request.getIdTutorados())) {
             return Professor.builder()
                     .name(request.getName())
                     .build();
            } else {
                return Professor.builder()
                     .name(request.getName())
                     .tutorados(request.getIdTutorados().stream()
                        .map(id -> alunoRepository.findById(id))
                        .collect(Collectors.toList()))
                     .build();
            }
         }
    }
}
