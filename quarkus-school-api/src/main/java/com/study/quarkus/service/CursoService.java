package com.study.quarkus.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import javax.inject.Inject;

import com.study.quarkus.dto.Curso.CursoRequest;
import com.study.quarkus.dto.Curso.CursoResponse;
import com.study.quarkus.mapper.CursoMapper;
import com.study.quarkus.model.Curso;
import com.study.quarkus.model.Disciplina;
import com.study.quarkus.repository.CursoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
@RequiredArgsConstructor
public class CursoService {
    @Inject
    CursoRepository repository;

    private final CursoMapper mapper;

    public List<String> getDisciplinasByIdCurso(int id) {
        Curso entity = repository.findById(id);
        List<String> response = mapper.toResponse(entity).getDisciplinas();
        return response ;
    }

    @Transactional
    public CursoResponse cadastraCurso(CursoRequest curso) {
        log.info("Curso cadastrado ::: descricao = {} ::: duracao = {}", curso.getDescricao(), curso.getDuracao());

        Curso entity = Curso.builder()
                                .descricao(curso.getDescricao())
                                .duracao(curso.getDuracao())
                                .build();
                                
        repository.persistAndFlush(entity);
        return mapper.toResponse(entity);

    }

    public List<CursoResponse> getCursos() {
        log.info("Listando todos os cursos");

        List<Curso> listaCursos = repository.listAll();
        return mapper.toResponse(listaCursos);

    }

    public CursoResponse getCursoById(int id) {
        log.info("Listando curso de id {}", id);

        Curso curso = repository.findById(id);
        return mapper.toResponse(curso);

    }

    @Transactional
    public CursoResponse updateDescricao(int id, String name) {
        Optional<Curso> curso = repository.findByIdOptional(id);

        String oldNome = curso.get().getDescricao();
        log.info("Updating curso id - {}, nome de {} para {}", id, oldNome, name);


        if (curso.isPresent()) {
            var entity = curso.get();
            entity.setDescricao(name);
            repository.persistAndFlush(entity);
            return mapper.toResponse(entity);
        }

        return new CursoResponse();
    }

    @Transactional
    public void deleteCurso(int id) {
        repository.delete(repository.findById(id));
    }

    
}
