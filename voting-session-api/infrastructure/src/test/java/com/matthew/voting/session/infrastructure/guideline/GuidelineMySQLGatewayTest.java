package com.matthew.voting.session.infrastructure.guideline;

import com.matthew.voting.session.domain.guideline.Guideline;
import com.matthew.voting.session.infrastructure.MySQLGatewayTest;
import com.matthew.voting.session.infrastructure.guideline.persistence.GuidelineRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@MySQLGatewayTest
public class GuidelineMySQLGatewayTest {

    @Autowired
    private GuidelineMySQLGateway guidelineGateway;

    @Autowired
    private GuidelineRepository guidelineRepository;

    @Test
    public void givenAValidGuideline_whenCallsCreate_shouldReturnANewGuideline() {
        final var expectedTitle = "Título da Pauta";
        final var expectedDescription = "Descrição da pauta";

        final var aGuideline = Guideline.newGuideline(expectedTitle, expectedDescription);

        Assertions.assertEquals(0, guidelineRepository.count());

        final var actualCategory = guidelineGateway.save(aGuideline);

        Assertions.assertEquals(1, guidelineRepository.count());

        Assertions.assertEquals(aGuideline.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedTitle, actualCategory.getTitle());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(aGuideline.getCreatedAt(), actualCategory.getCreatedAt());
        Assertions.assertEquals(aGuideline.getUpdatedAt(), actualCategory.getUpdatedAt());
        Assertions.assertEquals(aGuideline.getDeletedAt(), actualCategory.getDeletedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());

        final var actualEntity = guidelineRepository.findById(actualCategory.getId().getValue()).get();

        Assertions.assertEquals(aGuideline.getId().getValue(), actualEntity.getId());
        Assertions.assertEquals(expectedTitle, actualEntity.getTitle());
        Assertions.assertEquals(expectedDescription, actualEntity.getDescription());
        Assertions.assertEquals(aGuideline.getCreatedAt(), actualEntity.getCreatedAt());
        Assertions.assertEquals(aGuideline.getUpdatedAt(), actualEntity.getUpdatedAt());
        Assertions.assertEquals(aGuideline.getDeletedAt(), actualEntity.getDeletedAt());
        Assertions.assertNull(actualEntity.getDeletedAt());
    }

}
