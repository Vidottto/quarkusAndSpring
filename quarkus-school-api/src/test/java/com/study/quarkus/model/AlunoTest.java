package com.study.quarkus.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.persistence.Id;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class AlunoTest {
    private static final String NAME = "some name 1";
    private static final Integer ID = 100;
    private static final Professor TUTOR = Professor.builder().id(ID).name(NAME).build();
    
    @Test
    void constructorBuilder() {

        //GIVEN
        final var factory = Validation.buildDefaultValidatorFactory();
        final var validator = factory.getValidator();

        //WHEN
        var entity =
                Aluno.builder()
                        .id(ID)
                        .nome(NAME)
                        .tutor(TUTOR)
                        .build();

        //THEN
        assertFields(validator, entity);
        factory.close();
    }

    @Test
    void constructorAllArgs() {
        //GIVEN
        final var factory = Validation.buildDefaultValidatorFactory();
        final var validator = factory.getValidator();

        //WHEN
        var entity = new Aluno(ID, NAME, TUTOR);

        //THEN
        assertFields(validator, entity);
        factory.close();
    }

    @Test
    void constructorDefault() {
        //GIVEN
        final var factory = Validation.buildDefaultValidatorFactory();
        final var validator = factory.getValidator();

        //WHEN
        var entity = new Aluno();
        entity.setId(ID);
        entity.setNome(NAME);
        entity.setTutor(TUTOR);

        //THEN
        assertFields(validator, entity);
        factory.close();
    }
    

    @ParameterizedTest
    @MethodSource("invalidFields")
    void constructor_NotAllowed(final String name, final String errorMessage) {
        //GIVEN
        final var factory = Validation.buildDefaultValidatorFactory();
        final var validator = factory.getValidator();

        //WHEN
        var entity = new Aluno();
        entity.setId(ID);
        entity.setNome(name);
        entity.setTutor(TUTOR);

        //THEN
        final var violations = validator.validate(entity);
        assertThat(violations)
                .isNotEmpty()
                .hasSize(1);
        assertThat(violations.stream().findFirst().get().getMessage())
                .isEqualTo(errorMessage);
        factory.close();
    }

    @Test
    void equalsAndHashcodeContract() {
        EqualsVerifier.simple().forClass(Aluno.class)
                .withIgnoredAnnotations(Id.class)
                .withPrefabValues(Professor.class,
                        Professor.builder().name("prof1").build(), new Professor())
                .verify();
    }

    static Stream<Arguments> invalidFields() {
        return Stream.of(
                arguments(null, "O nome do aluno deve constar no cadastro"),
                arguments("", "O nome do aluno deve constar no cadastro")
        );
    }

    private void assertFields(final Validator validator, final Aluno entity) {
        final var violations = validator.validate(entity);
        assertThat(violations).isEmpty();

        assertThat(entity.getNome()).isEqualTo(NAME);
    }
}