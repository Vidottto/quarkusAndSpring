package com.study.quarkus.service;

import com.study.quarkus.dto.ProfessorRequest;
import com.study.quarkus.dto.ProfessorResponse;
import com.study.quarkus.mapper.ProfessorMapper;
import com.study.quarkus.model.ProfessorModel;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;


import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Slf4j
@RequiredArgsConstructor
public class ProfessorService {
    private final ProfessorMapper mapper;

    public List<ProfessorResponse> retrieveAll() {
        log.info("Listing professors");
        final List<ProfessorModel> listOfProfessors = ProfessorModel.listAll();
        return mapper.toResponse(listOfProfessors);
    }

    public ProfessorResponse getProfessorById(int id) {
        log.info("Listing professor de id {}", id);
        final Optional<ProfessorModel> professor = ProfessorModel.findByIdOptional(id);
        if(professor.isPresent()) {
            return mapper.toResponse(professor.get());
        }
        return new ProfessorResponse();
    }

    @Transactional
    public ProfessorResponse save(ProfessorRequest professorRequest) {

        log.info("Saving professor - {}", professorRequest);

        ProfessorModel entity =
                ProfessorModel.builder()
                .name(professorRequest.getName())
                .build();

        entity.persistAndFlush();

        return mapper.toResponse(entity);
    }

    @Transactional
    public Response delete(int id) {
        
        log.info("Deletando professor - {}", ProfessorModel.findByIdOptional(id).get());

        ProfessorModel.findByIdOptional(id).ifPresent(PanacheEntityBase::delete);

        return Response.ok().build();
    }

    @Transactional
    public ProfessorResponse update(int id, String name) {
        Optional<ProfessorModel> professor = ProfessorModel.findByIdOptional(id);

        String oldNome = professor.get().getName();
        log.info("Updating professor id - {}, nome de {} para {}", id, oldNome, name);


        if (professor.isPresent()) {
            var entity = professor.get();
            entity.setName(name);
            entity.persistAndFlush();
            return mapper.toResponse(entity);
        }

        return new ProfessorResponse();
    }


}
