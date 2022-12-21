package com.study.quarkus.mapper;

import com.study.quarkus.dto.AlunoRequest;
import com.study.quarkus.dto.AlunoResponse;
import com.study.quarkus.model.AlunoModel;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class AlunoMapper {
    public List<AlunoResponse> toResponse(List<AlunoModel> listOfAlunos) {

        if (Objects.isNull(listOfAlunos)) return new ArrayList<>();

        return listOfAlunos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public AlunoResponse toResponse(AlunoModel entity) {

        if (Objects.isNull(entity)) return null;

        return  AlunoResponse.builder()
                    .id(entity.getId())
                    .name(entity.getNome())
                    .build();
    }

    public AlunoModel toEntity(AlunoRequest request) {
         if (Objects.isNull(request)) {
             return null;
         } else {
             return AlunoModel.builder()
                     .nome(request.getNome())
                     .build();
         }
    }
}
