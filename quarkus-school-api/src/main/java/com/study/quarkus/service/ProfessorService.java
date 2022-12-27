package com.study.quarkus.service;

import com.study.quarkus.dto.Professor.ProfessorRequest;
import com.study.quarkus.dto.Professor.ProfessorResponse;
import com.study.quarkus.mapper.ProfessorMapper;
import com.study.quarkus.model.Professor;
import com.study.quarkus.repository.ProfessorRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Slf4j
@RequiredArgsConstructor
public class ProfessorService {
    private final ProfessorMapper mapper;

    @Inject
    ProfessorRepository repository;

    public List<ProfessorResponse> retrieveAll() {
        log.info("Listing professors");
        final List<Professor> listOfProfessors = repository.listAll();
        return mapper.toResponse(listOfProfessors);
    }

    public ProfessorResponse getProfessorById(int id) {
        log.info("Listing professor de id {}", id);
        final Optional<Professor> professor = repository.findByIdOptional(id);
        if(professor.isPresent()) {
            return mapper.toResponse(professor.get());
        }
        return new ProfessorResponse();
    }

    @Transactional
    public ProfessorResponse save(ProfessorRequest professorRequest) {

        log.info("Saving professor - {}", professorRequest);

        Professor entity =
                Professor.builder()
                .name(professorRequest.getName())
                .build();

        repository.persistAndFlush(entity);

        return mapper.toResponse(entity);
    }

    @Transactional
    public Response delete(int id) {
        
        log.info("Deletando professor - {}", repository.findByIdOptional(id).get());

        repository.deleteById(id);

        return Response.ok().build();
    }

    @Transactional
    public ProfessorResponse update(int id, String name) {
        Optional<Professor> professor = repository.findByIdOptional(id);

        String oldNome = professor.get().getName();
        log.info("Updating professor id - {}, nome de {} para {}", id, oldNome, name);


        if (professor.isPresent()) {
            var entity = professor.get();
            entity.setName(name);
            repository.persistAndFlush(entity);
            return mapper.toResponse(entity);
        }

        return new ProfessorResponse();
    }


}
