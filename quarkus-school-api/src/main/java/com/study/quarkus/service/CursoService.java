package com.study.quarkus.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import javax.inject.Inject;
import com.study.quarkus.dto.CursoRequest;
import com.study.quarkus.dto.CursoResponse;
import com.study.quarkus.mapper.CursoMapper;
import com.study.quarkus.model.CursoModel;
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

    @Transactional
    public CursoResponse cadastraCurso(CursoRequest curso) {
        log.info("Curso cadastrado ::: descricao = {} ::: duracao = {}", curso.getDescricao(), curso.getDuracao());

        CursoModel entity = CursoModel.builder()
                                .descricao(curso.getDescricao())
                                .duracao(curso.getDuracao())
                                .build();
                                
        repository.persistAndFlush(entity);
        return mapper.toResponse(entity);

    }

    public List<CursoResponse> getCursos() {
        log.info("Listando todos os cursos");

        List<CursoModel> listaCursos = repository.listAll();
        return mapper.toResponse(listaCursos);

    }

    @Transactional
    public CursoResponse update(int id, String name) {
        Optional<CursoModel> curso = repository.findByIdOptional(id);

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

    
}
