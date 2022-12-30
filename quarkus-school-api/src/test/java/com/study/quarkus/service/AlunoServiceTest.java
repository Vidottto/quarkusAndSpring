package com.study.quarkus.service;

import com.study.quarkus.dto.Aluno.AlunoRequest;
import com.study.quarkus.dto.Aluno.AlunoResponse;
import com.study.quarkus.dto.Professor.ProfessorResponse;
import com.study.quarkus.mapper.AlunoMapper;
import com.study.quarkus.mapper.ProfessorMapper;
import com.study.quarkus.model.Aluno;
import com.study.quarkus.model.Professor;
import com.study.quarkus.repository.AlunoRepository;
import com.study.quarkus.repository.ProfessorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class AlunoServiceTest {
    
    
    private ProfessorService professorService = Mockito.mock(ProfessorService.class);    

    private static final int ID = 300;
    private AlunoMapper mapper = Mockito.mock(AlunoMapper.class);
    private AlunoRepository repository = Mockito.mock(AlunoRepository.class);
    private ProfessorRepository professorRepository = Mockito.mock(ProfessorRepository.class);
    private ProfessorMapper professorMapper = Mockito.mock(ProfessorMapper.class);

    private Aluno entity1 = Mockito.mock(Aluno.class);
    private Aluno entity2 = Mockito.mock(Aluno.class);

    private AlunoResponse response1 = Mockito.mock(AlunoResponse.class);
    private AlunoResponse response2 = Mockito.mock(AlunoResponse.class);

    private AlunoService service = new AlunoService(mapper, repository, professorMapper, professorRepository);

    @Test
    void retrieveAll() {
        //GIVEN
        var listOfResponses = List.of(response1, response2);
        var listOfEntities  = List.of(entity1, entity2);
        given(repository.listAll())
                .willReturn(listOfEntities);
        given(mapper.toResponse(listOfEntities))
                .willReturn(listOfResponses);

        //WHEN
        var actual = service.listarAlunos();

        //THEN
        assertThat(actual).isEqualTo(listOfResponses);
    }

    @Test
    void save() {
        //GIVEN
        AlunoRequest request = Mockito.mock(AlunoRequest.class);

        //TODO: refactor to mapper method
        var entity =
                Aluno.builder()
                        .nome(request.getNome())
                        .build();

        given(mapper.toResponse(entity))
                .willReturn(response1);

        //WHEN
        var actual = service.registraAluno(request);

        //THEN
        assertThat(actual).isEqualTo(response1);
        verify(repository, times(1)).persistAndFlush(entity);
    }

    @Test
    void getById() throws Exception {
        //GIVEN
        given(repository.findById(ID))
                .willReturn(entity1);
        given(mapper.toResponse(entity1))
                .willReturn(response1);

        //WHEN
        var actual = service.getAlunoById(ID);

        //THEN
        assertThat(actual).isEqualTo(response1);
    }

    @Test
    void updateTutor() {
        //GIVEN
        var idProfessor = 3;
        var professor = Mockito.mock(Professor.class);
        var expectedProfessor = Mockito.mock(ProfessorResponse.class);


        given(repository.findById(ID))
                .willReturn(entity1);
        given(professorRepository.findById(idProfessor))
                .willReturn(professor);
        given(professorMapper.toResponse(professor))
                .willReturn(expectedProfessor);

    }


    @Test
    void getTutoradosByProfessorId() {
        //GIVEN
        var idProfessor = 500;
        var professor = Mockito.mock(Professor.class);
        var listOfEntities  = List.of(entity1, entity2);
        var listOfResponses = List.of(response1, response2);

        given(professorRepository.findById(idProfessor))
                .willReturn(professor);
        given(professorService.getTutoradosByProfessorId(idProfessor))
                .willReturn(listOfResponses);
        given(mapper.toResponse(listOfEntities))
                .willReturn(listOfResponses);

        //WHEN
        var actual = professorService.getTutoradosByProfessorId(idProfessor);

        //THEN
        assertThat(actual).isEqualTo(listOfResponses);
    }

}