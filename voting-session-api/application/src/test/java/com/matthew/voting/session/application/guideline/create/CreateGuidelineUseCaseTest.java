package com.matthew.voting.session.application.guideline.create;

import com.matthew.voting.session.domain.exceptions.DomainException;
import com.matthew.voting.session.domain.guideline.GuidelineGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;

@ExtendWith(MockitoExtension.class)
public class CreateGuidelineUseCaseTest {

    @InjectMocks
    private DefaultCreateGuidelineUseCase useCase;

    @Mock
    private GuidelineGateway gateway;

    @Test
    public void givenAValidCommand_whenCallsCreateGuideline_thenShouldReturnGuidelineId() {
        final var expectedTitle = "Titulo da Pauta";
        final var expectedDescription = "Descrição da pauta.";

        final var aCommand = CreateGuidelineCommand.with(expectedTitle, expectedDescription);

        Mockito.when(gateway.create(Mockito.any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand);

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(gateway, Mockito.times(1))
                .create(Mockito.argThat(aGuideline -> Objects.nonNull(aGuideline.getId())
                            && Objects.equals(expectedTitle, aGuideline.getTitle())
                            && Objects.equals(expectedDescription, aGuideline.getDescription())
                            && Objects.nonNull(aGuideline.getCreatedAt())
                            && Objects.nonNull(aGuideline.getUpdatedAt())
                            && Objects.isNull(aGuideline.getDeletedAt())));
    }

    @Test
    public void givenAValidCommandWithoutDescription_whenCallsCreateGuideline_thenShouldReturnGuidelineId() {
        final var expectedTitle = "Titulo da Pauta";
        final var expectedDescription = "";

        final var aCommand = CreateGuidelineCommand.with(expectedTitle, expectedDescription);

        Mockito.when(gateway.create(Mockito.any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand);

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(gateway, Mockito.times(1))
                .create(Mockito.argThat(aGuideline -> Objects.nonNull(aGuideline.getId())
                        && Objects.equals(expectedTitle, aGuideline.getTitle())
                        && Objects.equals(expectedDescription, aGuideline.getDescription())
                        && Objects.nonNull(aGuideline.getCreatedAt())
                        && Objects.nonNull(aGuideline.getUpdatedAt())
                        && Objects.isNull(aGuideline.getDeletedAt())));
    }

    @Test
    public void givenANullTitle_whenCallsCreateGuideline_thenShouldReturnDomainException() {
        final var expectedDescription = "Descrição da pauta.";
        final var expectedErrorMessage = "'title' should not be null";
        final var expectedErrorCount = 1;

        final var aCommand = CreateGuidelineCommand.with(null, expectedDescription);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> useCase.execute(aCommand));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

        Mockito.verify(gateway, Mockito.times(0)).create(Mockito.any());
    }

    @Test
    public void givenAnEmptyTitle_whenCallsCreateGuideline_thenShouldReturnDomainException() {
        final var expectedDescription = "Descrição da pauta.";
        final var expectedErrorMessage = "'title' should not be empty";
        final var expectedErrorCount = 1;

        final var aCommand = CreateGuidelineCommand.with("   ", expectedDescription);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> useCase.execute(aCommand));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

        Mockito.verify(gateway, Mockito.times(0)).create(Mockito.any());
    }

    @Test
    public void givenATitleMoreTran127_whenCallsCreateGuideline_thenShouldReturnDomainException() {
        final var expectedTitle = """
                Percebemos, cada vez mais, que a estrutura atual da organização prepara-nos para enfrentar 
                situações atípicas decorrentes das direções preferenciais no sentido do progresso.""";
        final var expectedDescription = "Descrição da pauta.";
        final var expectedErrorMessage = "'title' must not exceed 127 characters";
        final var expectedErrorCount = 1;

        final var aCommand = CreateGuidelineCommand.with(expectedTitle, expectedDescription);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> useCase.execute(aCommand));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

        Mockito.verify(gateway, Mockito.times(0)).create(Mockito.any());
    }

    @Test
    public void givenADescriptionMoreTran255_whenCallsCreateGuideline_thenShouldReturnDomainException() {
        final var expectedTitle = "Título da Pauta";
        final var expectedDescription = """
                Percebemos, cada vez mais, que a estrutura atual da organização prepara-nos para enfrentar 
                situações atípicas decorrentes das direções preferenciais no sentido do progresso.
                Percebemos, cada vez mais, que a estrutura atual da organização prepara-nos para enfrentar 
                situações atípicas decorrentes das direções preferenciais no sentido do progresso.""";
        final var expectedErrorMessage = "'description' must not exceed 255 characters";
        final var expectedErrorCount = 1;

        final var aCommand = CreateGuidelineCommand.with(expectedTitle, expectedDescription);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> useCase.execute(aCommand));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

        Mockito.verify(gateway, Mockito.times(0)).create(Mockito.any());
    }

    @Test
    public void givenAValidCommand_whenGatewayThrowsException_thenShouldReturnAnException() {
        final var expectedTitle = "Titulo da Pauta";
        final var expectedDescription = "Descrição da pauta.";
        final var expectedErrorMessage = "Gateway Error";

        final var aCommand = CreateGuidelineCommand.with(expectedTitle, expectedDescription);

        Mockito.when(gateway.create(Mockito.any()))
                .thenThrow(new IllegalStateException(expectedErrorMessage));

        final var actualException = Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(aCommand));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

        Mockito.verify(gateway, Mockito.times(1))
                .create(Mockito.argThat(aGuideline -> Objects.nonNull(aGuideline.getId())
                        && Objects.equals(expectedTitle, aGuideline.getTitle())
                        && Objects.equals(expectedDescription, aGuideline.getDescription())
                        && Objects.nonNull(aGuideline.getCreatedAt())
                        && Objects.nonNull(aGuideline.getUpdatedAt())
                        && Objects.isNull(aGuideline.getDeletedAt())));
    }
}
