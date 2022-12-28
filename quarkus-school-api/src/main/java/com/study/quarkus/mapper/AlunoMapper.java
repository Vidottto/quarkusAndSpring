package com.study.quarkus.mapper;

import com.study.quarkus.dto.Aluno.AlunoRequest;
import com.study.quarkus.dto.Aluno.AlunoResponse;
import com.study.quarkus.model.Aluno;
import com.study.quarkus.repository.ProfessorRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class AlunoMapper {

    @Inject
    ProfessorRepository professorRepository;

    public List<AlunoResponse> toResponse(List<Aluno> listOfAlunos) {

        if (Objects.isNull(listOfAlunos)) return new ArrayList<>();

        return listOfAlunos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public AlunoResponse toResponse(Aluno entity) {

        if (Objects.isNull(entity)) return null;

        if (!Objects.isNull(entity.getTutor())) {
            return  AlunoResponse.builder()
                    .id(entity.getId())
                    .tutor(entity.getTutor().getName())
                    .name(entity.getNome())
                    .build();
        } else {

        return  AlunoResponse.builder()
                    .id(entity.getId())
                    .name(entity.getNome())
                    .build();
        }
    }

    public Aluno toEntity(AlunoRequest request) {
         if (Objects.isNull(request)) {
             return null;
         } else {
            if (!Objects.isNull(request.getTutorId())){
                return Aluno.builder()
                .nome(request.getNome())
                .tutor(professorRepository.findById(request.getTutorId()))
                .build();
            } else {
                return Aluno.builder()
                .nome(request.getNome())
                .build();
            }
             
         }
    }
}
