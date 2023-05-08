package com.matthew.voting.session.domain.guideline;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GuidelineTest {

    @Test
    public void givenAValidParams_whenCallNewGuideline_thenInstantiateAGuideline() {
        final var title = "Título da Pauta";
        final var description = "Descrição da Pauta";

        final var actualGuideline = Guideline.newGuideline(title, description);

        Assertions.assertNotNull(actualGuideline);
        Assertions.assertNotNull(actualGuideline.getId());
        Assertions.assertEquals(title, actualGuideline.getTitle());
        Assertions.assertEquals(description, actualGuideline.getDescription());
        Assertions.assertNotNull(actualGuideline.getCreatedAt());
        Assertions.assertNotNull(actualGuideline.getUpdatedAt());
        Assertions.assertNull(actualGuideline.getDeletedAt());
    }
}
