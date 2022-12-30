package com.study.quarkus.repository;

import com.study.quarkus.model.Aluno;
import com.study.quarkus.model.Professor;
import com.study.quarkus.service.ProfessorService;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class AlunoRepositoryTest {

    @Inject
    AlunoRepository repository;

    @Inject
    ProfessorRepository professorRepository;

    @Inject
    ProfessorService professorService;

    @Test
    void getTutoradosByProfessor() {

        //GIVEN
        var professorOne = Professor.builder().name("Professor X").build();
        var professorTwo = Professor.builder().name("Professor Y").build();
        saveToRepository(professorRepository, professorOne, professorTwo);

        var alunoOne = Aluno.builder().nome("Jose").tutor(professorOne).build();
        var alunoTwo = Aluno.builder().nome("Maria").tutor(professorTwo).build();
        var alunoThree = Aluno.builder().nome("Joao").tutor(professorOne).build();
        saveToRepository(repository, alunoOne, alunoTwo, alunoThree);

        //WHEN
        var actual = professorService.getTutoradosByProfessorId(professorOne.getId());

        //THEN
        assertThat(actual.size()).isEqualTo(2);
        assertThat(actual.get(0).getName()).isEqualTo("Jose");
        assertThat(actual.get(1).getName()).isEqualTo("Joao");
    }

    @Transactional
    void saveToRepository(PanacheRepositoryBase repository, Object ... entities) {
        for(Object entity: entities) {
            repository.persist(entity);
        }
    }


}