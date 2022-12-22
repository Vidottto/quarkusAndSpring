package com.study.quarkus.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import com.study.quarkus.dto.CursoRequest;
import com.study.quarkus.dto.CursoResponse;
import com.study.quarkus.model.CursoModel;

@ApplicationScoped
public class CursoMapper {
    public List<CursoResponse> toResponse(List<CursoModel> listOfCursos) {

        if (Objects.isNull(listOfCursos)) return new ArrayList<>();

        return listOfCursos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public CursoResponse toResponse(CursoModel entity) {

        if (Objects.isNull(entity)) return null;

        return  CursoResponse.builder()
                    .id(entity.getId())
                    .descricao(entity.getDescricao())
                    .duracao(entity.getDuracao())
                    .build();
    }

    public CursoModel toEntity(CursoRequest request) {
         if (Objects.isNull(request)) {
             return null;
         } else {
             return CursoModel.builder()
                     .descricao(request.getDescricao())
                    //  .duracao(request.getDuracao())
                     .build();
         }
    }
}
