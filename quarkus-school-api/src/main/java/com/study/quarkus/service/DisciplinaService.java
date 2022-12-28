package com.study.quarkus.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

import com.study.quarkus.dto.Disciplina.DisciplinaRequest;
import com.study.quarkus.dto.Disciplina.DisciplinaResponse;
import com.study.quarkus.mapper.DisciplinaMapper;
import com.study.quarkus.model.Curso;
import com.study.quarkus.model.Disciplina;
import com.study.quarkus.model.Professor;
import com.study.quarkus.repository.CursoRepository;
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

    @Inject 
    CursoRepository cursoRepository;

    @Transactional
    public DisciplinaResponse registraDisciplina(DisciplinaRequest disciplinaRequest) {
        Disciplina entity = mapper.toEntity(disciplinaRequest);
        Curso curso = cursoRepository.findById(disciplinaRequest.getCurso());
        curso.getDisciplinas().add(entity);
        repository.persistAndFlush(entity);
        DisciplinaResponse response = mapper.toResponse(entity);
        log.info("registrando disciplina {}", response);

        return response;
        
    }

    @Transactional
    public Response deletaDisciplinaById(int id) {
        repository.deleteById(id);
        return Response.ok().build();
    }

    @Transactional
    public DisciplinaResponse atualizaProfessorByDisciplinaId (int id, int idProfessorNovo) {
        Disciplina disciplina = repository.findById(id);
        Professor professorNovo = professorRepository.findById(idProfessorNovo);

        disciplina.setProfessor(professorNovo);
        
        return mapper.toResponse(disciplina);
    }

}
