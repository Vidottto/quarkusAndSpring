package com.study.quarkus.service;

import com.study.quarkus.dto.Aluno.AlunoResponse;
import com.study.quarkus.dto.Professor.ProfessorRequest;
import com.study.quarkus.dto.Professor.ProfessorResponse;
import com.study.quarkus.mapper.AlunoMapper;
import com.study.quarkus.mapper.ProfessorMapper;
import com.study.quarkus.model.Aluno;
import com.study.quarkus.model.Professor;
import com.study.quarkus.repository.ProfessorRepository;
import com.study.quarkus.repository.AlunoRepository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
@Slf4j
@RequiredArgsConstructor
public class ProfessorService {
    private final ProfessorMapper mapper;

    @Inject
    ProfessorRepository repository;

    @Inject
    AlunoMapper alunoMapper;
    
    @Inject
    AlunoRepository alunoRepository;

    public List<ProfessorResponse> retrieveAll() {
        log.info("Listing professors");
        List<Professor> listProfessores = repository.listAll();

        return mapper.toResponse(listProfessores);
    }

    public ProfessorResponse getProfessorById(int id) {
        log.info("Listing professor de id {}", id);
        Optional<Professor> professor = repository.findByIdOptional(id);
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
    public void delete(int id) {
        
        log.info("Deletando professor - {}", repository.findByIdOptional(id).get());
        Professor professor = repository.findById(id);
        if (Objects.nonNull(professor.getTutorados()) || !professor.getTutorados().isEmpty()) {
            professor.getTutorados().forEach(a -> a.setTutor(null));
        }
        repository.deleteById(id);

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

    public List<AlunoResponse> getTutoradosByProfessorId(int id) {
        Professor tutor = repository.findById(id);
        List<AlunoResponse> listTutorados = alunoMapper.toResponse(tutor.getTutorados());
        
        return listTutorados;
    }

    @Transactional
    public ProfessorResponse updateTutoradoByIdProfessor(int id, int idTutorado) {
        Professor tutor = repository.findById(id);
        Aluno tutorado = alunoRepository.findById(idTutorado);
        tutorado.setTutor(tutor);
        alunoRepository.persistAndFlush(tutorado);
        tutor.getTutorados().add(tutorado);
        repository.persistAndFlush(tutor);
        
        return mapper.toResponse(tutor);
    }

    @Transactional
    public ProfessorResponse deleteTutoradoByIdProfessor(int id, int tutoradoId) {
        Aluno tutorado = alunoRepository.findById(tutoradoId);
        tutorado.setTutor(null);

        
        return null;
    }



}
