package com.study.quarkus.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.POST;

import com.study.quarkus.dto.Disciplina.DisciplinaRequest;
import com.study.quarkus.dto.Disciplina.DisciplinaResponse;
import com.study.quarkus.mapper.DisciplinaMapper;
import com.study.quarkus.model.Disciplina;
import com.study.quarkus.repository.DisciplinaRepository;
import com.study.quarkus.repository.ProfessorRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@ApplicationScoped
public class DisciplinaService {

    @Inject
    DisciplinaRepository repository;

    @Inject
    DisciplinaMapper mapper;

    @Inject
    ProfessorRepository professorRepository;

    @POST
    @Transactional
    public DisciplinaResponse registraDisciplina(DisciplinaRequest disciplinaRequest) {
        Disciplina entity = Disciplina.builder()
                                    .creditos(disciplinaRequest.getCreditos())
                                    .professor(professorRepository.findById(disciplinaRequest.getProfessor()))
                                    .name(disciplinaRequest.getName())
                                    .build();
        repository.persistAndFlush(entity);
        log.info("registrando disciplina {}", entity);

        return mapper.toResponse(entity);
        
    }



}
