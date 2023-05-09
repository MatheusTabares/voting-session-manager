package com.matthew.voting.session.application.guideline.create;

import com.matthew.voting.session.domain.guideline.GuidelineGateway;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;

public class CreateGuidelineUseCaseTest {

    public void givenAValidCommand_whenCallsCreateGuideline_shouldReturnGuidelineId() {
        final var expectedTitle = "Titulo da Pauta";
        final var expectedDescription = "Descrição da pauta.";

        final var aCommand = CreateGuidelineCommand.with(expectedTitle, expectedDescription);

        final GuidelineGateway guidelineGateway = Mockito.mock(GuidelineGateway.class);

        Mockito.when(guidelineGateway.create(Mockito.any()))
                .thenAnswer(returnsFirstArg());

        final var useCase = CreateGuidelineUseCase(guidelineGateway);

        final var actualOutput = useCase.execute(aCommand);

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.getId());

        Mockito.verify(guidelineGateway, Mockito.times(1))
                .create(Mockito.argThat(aGuideline -> {
                    return Objects.nonNull(aGuideline.getId())
                            && Objects.equals(expectedTitle, aGuideline.getTitle())
                            && Objects.equals(expectedDescription, aGuideline.getDescription())
                            && Objects.nonNull(aGuideline.getCreatedAt())
                            && Objects.nonNull(aGuideline.getUpdatedAt())
                            && Objects.isNull(aGuideline.getDeletedAt());
                }));
    }
}
