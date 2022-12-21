package com.study.quarkus.service;
// package com.study.quarkus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

// import java.util.HashMap;
import java.util.List;



import com.study.quarkus.dto.AlunoRequest;
import com.study.quarkus.dto.AlunoResponse;
import com.study.quarkus.mapper.AlunoMapper;
import com.study.quarkus.model.AlunoModel;
import com.study.quarkus.repository.AlunoRepository;

import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class AlunoService {

    @Inject
    AlunoMapper mapper;

    @Inject
    AlunoRepository repository;

    public Response listarAlunos(){
        log.info("Lista de alunos\n\n");
        List<AlunoModel> alunos = repository.listAll();

        return Response.ok(alunos).build();
    }

    public AlunoResponse getAlunoById(int id){
        log.info("Aluno de id {} listado\n\n", id);
        AlunoModel aluno = repository.findById(id);
        return mapper.toResponse(aluno);
    }
    
    @Transactional
    public AlunoResponse registraAluno(AlunoRequest aluno) {
        
        log.info("Aluno registrado ::: {}", aluno);
        AlunoModel entity = AlunoModel
                .builder()
                .nome(aluno.getNome())
                .build();

        repository.persistAndFlush(entity);
        return mapper.toResponse(entity);
    }

    @Transactional
    public AlunoResponse modifcaAluno(int id, String novoNome) {
        AlunoModel aluno = repository.findById(id);
        log.info("Mudando nome ::: aluno de id {} ::: nome velho {} ::: nome novo {}", id, aluno.getNome(), novoNome);
        aluno.setNome(novoNome);
        return mapper.toResponse(aluno);
    }

    @Transactional
    public AlunoResponse deletaAluno(int id) {
        AlunoModel aluno = repository.findById(id);
        log.info("Deletado da DB o aluno {}", aluno);
        repository.delete(aluno);
        return mapper.toResponse(aluno);
    }

    
}
