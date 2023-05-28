package com.matthew.voting.session.application.guideline.openSession;

import com.matthew.voting.session.domain.exceptions.DomainException;
import com.matthew.voting.session.domain.guideline.Guideline;
import com.matthew.voting.session.domain.guideline.GuidelineGateway;
import com.matthew.voting.session.domain.validation.Error;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultOpenSessionGuidelineUseCase extends OpenSessionGuidelineUseCase{

    private final GuidelineGateway guidelineGateway;

    public DefaultOpenSessionGuidelineUseCase(final GuidelineGateway guidelineGateway) {
        this.guidelineGateway = Objects.requireNonNull(guidelineGateway);
    }

    @Override
    public void execute(OpenSessionGuidelineCommand aCommand) {
        final var guidelineId = aCommand.guidelineId();
        final var aGuideline = this.guidelineGateway.findById(guidelineId)
                .orElseThrow(notFound(guidelineId));
        final var anOpenGuideline = Guideline.openSession(aGuideline);
        this.guidelineGateway.save(anOpenGuideline);
        //TODO: chamar o schedule passando o tempo
    }

    private static Supplier<DomainException> notFound(String guidelineId) {
        return () -> DomainException.with(new Error("Guideline with ID %s was not found".formatted(guidelineId)));
    }
}
