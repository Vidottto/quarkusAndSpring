package com.study.quarkus.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import java.util.List;

import com.study.quarkus.dto.Aluno.AlunoRequest;
import com.study.quarkus.dto.Aluno.AlunoResponse;
import com.study.quarkus.dto.Professor.ProfessorResponse;
import com.study.quarkus.mapper.AlunoMapper;
import com.study.quarkus.mapper.ProfessorMapper;
import com.study.quarkus.model.Aluno;
import com.study.quarkus.model.Professor;
import com.study.quarkus.repository.AlunoRepository;
import com.study.quarkus.repository.ProfessorRepository;

import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class AlunoService {

    @Inject
    AlunoMapper mapper;

    @Inject
    AlunoRepository repository;

    @Inject
    ProfessorRepository professorRepository;

    @Inject
    ProfessorMapper professorMapper;

    public List<AlunoResponse> listarAlunos(){
        log.info("Lista de alunos\n\n");
        List<AlunoResponse> listAlunos = mapper.toResponse(repository.listAll());

        return listAlunos;
    }

    public AlunoResponse getAlunoById(int id){
        log.info("Aluno de id {} listado\n\n", id);
        Aluno aluno = repository.findById(id);

        return mapper.toResponse(aluno);
    }
    
    @Transactional
    public AlunoResponse registraAluno(AlunoRequest aluno) {
        
        log.info("Aluno registrado ::: {}", aluno);
        Aluno entity = Aluno
                .builder()
                .nome(aluno.getNome())
                .build();

        repository.persistAndFlush(entity);

        return mapper.toResponse(entity);
    }

    @Transactional
    public AlunoResponse modifcaAluno(int id, String novoNome) {
        Aluno aluno = repository.findById(id);
        log.info("Mudando nome ::: aluno de id {} ::: nome velho {} ::: nome novo {}", id, aluno.getNome(), novoNome);
        aluno.setNome(novoNome);

        return mapper.toResponse(aluno);
    }

    @Transactional
    public AlunoResponse deletaAluno(int id) {
        Aluno aluno = repository.findById(id);
        log.info("Deletado da DB o aluno {}", aluno);
        repository.delete(aluno);
        
        return mapper.toResponse(aluno);
    }

    @Transactional
    public AlunoResponse modifcaTutorAluno(int id, int idNovoTutor) {
        Aluno aluno = repository.findById(id);
        Professor tutor = professorRepository.findById(idNovoTutor);
        aluno.setTutor(tutor);
        repository.persistAndFlush(aluno);
        
        return mapper.toResponse(aluno);
    }

    public ProfessorResponse getTutorByAlunoId(int id) {
        Aluno aluno = repository.findById(id);
        ProfessorResponse tutor = professorMapper.toResponse(aluno.getTutor());

        return tutor;
    }

    
}
