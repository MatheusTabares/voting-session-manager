package com.matthew.voting.session.infrastructure.guideline.persistence;

import com.matthew.voting.session.domain.guideline.Guideline;
import com.matthew.voting.session.infrastructure.MySQLGatewayTest;
import org.hibernate.PropertyValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

@MySQLGatewayTest
public class GuidelineRepositoryTest {

    @Autowired
    private GuidelineRepository repository;

    @Test
    public void givenAnInvalidNullTitle_whenCallsSave_shouldReturnError() {
        final var expectedProperty = "title";
        final var expectedMessage = "not-null property references a null or transient value : com.matthew.voting.session.infrastructure.guideline.persistence.GuidelineJpaEntity.title";
        final var aGuideline = Guideline.newGuideline("Título da Pauta", "Descrição da pauta");

        final var anEntity = GuidelineJpaEntity.from(aGuideline);
        anEntity.setTitle(null);

        final var actualException = Assertions.assertThrows(DataIntegrityViolationException.class, () -> repository.save(anEntity));
        final var actualCause = Assertions.assertInstanceOf(PropertyValueException.class, actualException.getCause());

        Assertions.assertEquals(expectedProperty, actualCause.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualCause.getMessage());
    }

    @Test
    public void givenAnInvalidNullCreatedAt_whenCallsSave_shouldReturnError() {
        final var expectedProperty = "createdAt";
        final var expectedMessage = "not-null property references a null or transient value : com.matthew.voting.session.infrastructure.guideline.persistence.GuidelineJpaEntity.createdAt";
        final var aGuideline = Guideline.newGuideline("Título da Pauta", "Descrição da pauta");

        final var anEntity = GuidelineJpaEntity.from(aGuideline);
        anEntity.setCreatedAt(null);

        final var actualException = Assertions.assertThrows(DataIntegrityViolationException.class, () -> repository.save(anEntity));
        final var actualCause = Assertions.assertInstanceOf(PropertyValueException.class, actualException.getCause());

        Assertions.assertEquals(expectedProperty, actualCause.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualCause.getMessage());
    }

    @Test
    public void givenAnInvalidNullUpdatedAt_whenCallsSave_shouldReturnError() {
        final var expectedProperty = "updatedAt";
        final var expectedMessage = "not-null property references a null or transient value : com.matthew.voting.session.infrastructure.guideline.persistence.GuidelineJpaEntity.updatedAt";
        final var aGuideline = Guideline.newGuideline("Título da Pauta", "Descrição da pauta");

        final var anEntity = GuidelineJpaEntity.from(aGuideline);
        anEntity.setUpdatedAt(null);

        final var actualException = Assertions.assertThrows(DataIntegrityViolationException.class, () -> repository.save(anEntity));
        final var actualCause = Assertions.assertInstanceOf(PropertyValueException.class, actualException.getCause());

        Assertions.assertEquals(expectedProperty, actualCause.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualCause.getMessage());
    }
}
