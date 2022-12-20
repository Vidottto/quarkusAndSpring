package com.study.quarkus.mapper;

import com.study.quarkus.dto.ProfessorRequest;
import com.study.quarkus.dto.ProfessorResponse;
import com.study.quarkus.model.ProfessorModel;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProfessorMapper {
    public List<ProfessorResponse> toResponse(List<ProfessorModel> listOfProfessors) {

        if (Objects.isNull(listOfProfessors)) return new ArrayList<>();

        return listOfProfessors.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ProfessorResponse toResponse(ProfessorModel entity) {

        if (Objects.isNull(entity)) return null;

        return  ProfessorResponse.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .build();
    }

    public ProfessorModel toEntity(ProfessorRequest request) {
         if (Objects.isNull(request)) {
             return null;
         } else {
             return ProfessorModel.builder()
                     .name(request.getName())
                     .build();
         }
    }
}
