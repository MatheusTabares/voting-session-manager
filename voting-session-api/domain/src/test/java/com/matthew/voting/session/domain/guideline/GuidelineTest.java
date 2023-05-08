package com.matthew.voting.session.domain.guideline;

import com.matthew.voting.session.domain.exceptions.DomainException;
import com.matthew.voting.session.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GuidelineTest {

    @Test
    public void givenAValidParams_whenCallNewGuideline_thenInstantiateAGuideline() {
        final var title = "Título da Pauta";
        final var description = "Descrição da Pauta";

        final var actualGuideline = Guideline.newGuideline(title, description);

        Assertions.assertDoesNotThrow(() -> actualGuideline.validate(new ThrowsValidationHandler()));
        Assertions.assertNotNull(actualGuideline);
        Assertions.assertNotNull(actualGuideline.getId());
        Assertions.assertEquals(title, actualGuideline.getTitle());
        Assertions.assertEquals(description, actualGuideline.getDescription());
        Assertions.assertNotNull(actualGuideline.getCreatedAt());
        Assertions.assertNotNull(actualGuideline.getUpdatedAt());
        Assertions.assertNull(actualGuideline.getDeletedAt());
    }

    @Test
    public void givenANullTitle_whenCallNewGuidelineAndValidate_thenShouldReceiveAnError() {
        final String title = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'title' should not be null";
        final var description = "Descrição da Pauta";

        final var actualGuideline = Guideline.newGuideline(title, description);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualGuideline.validate(new ThrowsValidationHandler()));
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnEmptyTitle_whenCallNewGuidelineAndValidate_thenShouldReceiveAnError() {
        final String title = "   ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'title' should not be empty";
        final var description = "Descrição da Pauta";

        final var actualGuideline = Guideline.newGuideline(title, description);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualGuideline.validate(new ThrowsValidationHandler()));
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenATitleMoreThan127_whenCallNewGuidelineAndValidate_thenShouldReceiveAnError() {
        final String title = """
                Neste sentido, o desenvolvimento contínuo de distintas formas de atuação pode nos levar a considerar 
                a reestruturação do investimento em reciclagem técnica.
                Neste sentido, o desenvolvimento contínuo de distintas formas de atuação pode nos levar a considerar a 
                reestruturação do investimento em reciclagem técnica.
                """;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'title' must not exceed 127 characters";
        final var description = "Descrição da Pauta";

        final var actualGuideline = Guideline.newGuideline(title, description);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualGuideline.validate(new ThrowsValidationHandler()));
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenADescriptionMoreThan255_whenCallNewGuidelineAndValidate_thenShouldReceiveAnError() {
        final String title = "Título da Pauta";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'description' must not exceed 255 characters";
        final String description= """
                Neste sentido, o desenvolvimento contínuo de distintas formas de atuação pode nos levar a considerar 
                a reestruturação do investimento em reciclagem técnica.
                Neste sentido, o desenvolvimento contínuo de distintas formas de atuação pode nos levar a considerar a 
                reestruturação do investimento em reciclagem técnica.
                """;

        final var actualGuideline = Guideline.newGuideline(title, description);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualGuideline.validate(new ThrowsValidationHandler()));
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenWithoutDescription_whenCallNewGuideline_thenInstantiateAGuideline() {
        final var title = "Título da Pauta";
        final var description = "";

        final var actualGuideline = Guideline.newGuideline(title, description);

        Assertions.assertDoesNotThrow(() -> actualGuideline.validate(new ThrowsValidationHandler()));
        Assertions.assertNotNull(actualGuideline);
        Assertions.assertNotNull(actualGuideline.getId());
        Assertions.assertEquals(title, actualGuideline.getTitle());
        Assertions.assertEquals(description, actualGuideline.getDescription());
        Assertions.assertNotNull(actualGuideline.getCreatedAt());
        Assertions.assertNotNull(actualGuideline.getUpdatedAt());
        Assertions.assertNull(actualGuideline.getDeletedAt());
    }
}
