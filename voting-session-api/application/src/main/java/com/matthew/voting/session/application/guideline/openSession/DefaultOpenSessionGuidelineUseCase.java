package com.matthew.voting.session.application.guideline.openSession;

import com.matthew.voting.session.domain.exceptions.DomainException;
import com.matthew.voting.session.domain.guideline.Guideline;
import com.matthew.voting.session.domain.guideline.GuidelineGateway;
import com.matthew.voting.session.domain.validation.Error;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultOpenSessionGuidelineUseCase extends OpenSessionGuidelineUseCase{

    public static final int DEFAULT_DURATION_TIME_IN_SECONDS = 60;
    private final GuidelineGateway guidelineGateway;

    public DefaultOpenSessionGuidelineUseCase(final GuidelineGateway guidelineGateway) {
        this.guidelineGateway = Objects.requireNonNull(guidelineGateway);
    }

    @Override
    public void execute(OpenSessionGuidelineCommand aCommand) {
        final var guidelineId = aCommand.guidelineId();
        final var duration = Objects.isNull(aCommand.duration()) || aCommand.duration() <= 0 
                ? DEFAULT_DURATION_TIME_IN_SECONDS : aCommand.duration() ;

        final var aGuideline = this.guidelineGateway.findById(guidelineId)
                .orElseThrow(notFound(guidelineId));

        final var anOpenGuideline = Guideline.openSession(aGuideline, duration);
        this.guidelineGateway.save(anOpenGuideline);
    }

    private static Supplier<DomainException> notFound(String guidelineId) {
        return () -> DomainException.with(new Error("Guideline with ID %s was not found".formatted(guidelineId)));
    }
}
