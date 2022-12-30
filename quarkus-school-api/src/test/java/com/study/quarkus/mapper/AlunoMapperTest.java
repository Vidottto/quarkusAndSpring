package com.study.quarkus.mapper;

import com.study.quarkus.dto.Aluno.AlunoResponse;
import com.study.quarkus.dto.Professor.ProfessorResponse;
import com.study.quarkus.model.Aluno;
import com.study.quarkus.model.Professor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class AlunoMapperTest{

    private static final String NAME_1 = "some name 1";
    private static final String NAME_2 = "some name 2";
    private static final Integer ID_1 = 100;
    private static final Integer ID_2 = 200;
    private static final Professor TUTOR_1 = Professor.builder().id(ID_1).name(NAME_1).build();
    private static final Professor TUTOR_2 = Professor.builder().id(ID_2).name(NAME_2).build();
    private static final Aluno ENTITY_1 = new Aluno(ID_1, NAME_1, TUTOR_1);
    private static final Aluno ENTITY_2 = new Aluno(ID_2, NAME_2, TUTOR_2);

    private static final AlunoResponse RESPONSE_1 =
            new AlunoResponse(ID_1, NAME_1, TUTOR_1.getName());

    private static final AlunoResponse RESPONSE_2 =
            new AlunoResponse(ID_2, NAME_2, TUTOR_2.getName());

    private final AlunoMapper mapper = new AlunoMapper();

    private final ProfessorMapper professorMapper = new ProfessorMapper();

    @Test
    void toResponseList() {
        //GIVEN
        var listOfEntities = List.of(ENTITY_1, ENTITY_2);

        //WHEN
        var actual = mapper.toResponse(listOfEntities);

        //THEN
        assertThat(actual).containsExactly(RESPONSE_1, RESPONSE_2);

    }

    @Test
    void toResponseListNull() {
        //GIVEN

        //WHEN
        var actual = mapper.toResponse((List<Aluno>) null);

        //THEN
        assertThat(actual).isEmpty();

    }

    @Test
    void toResponseWithoutTutor() {
        //GIVEN
        var entity = new Aluno(ID_2, NAME_2, null);
        var expected = new AlunoResponse(ID_2, NAME_2, null);

        //WHEN
        var actual = mapper.toResponse(entity);

        //THEN
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    void toResponseTutor() {
        //GIVEN
        var expected = ProfessorResponse.builder().id(TUTOR_1.getId()).name(TUTOR_1.getName()).build();

        //WHEN
        var actual = professorMapper.toResponse(TUTOR_1);

        //THEN
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("atualizacao")
                .isEqualTo(expected);
    }
}