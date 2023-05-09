package com.matthew.voting.session.application.guideline.create;

import com.matthew.voting.session.domain.guideline.Guideline;
import com.matthew.voting.session.domain.guideline.GuidelineGateway;
import com.matthew.voting.session.domain.validation.handler.ThrowsValidationHandler;

import java.util.Objects;

public class DefaultCreateGuidelineUseCase extends CreateGuidelineUseCase{

    private final GuidelineGateway guidelineGateway;

    public DefaultCreateGuidelineUseCase(final GuidelineGateway guidelineGateway) {
        this.guidelineGateway = Objects.requireNonNull(guidelineGateway);
    }

    @Override
    public CreateGuidelineOutput execute(final CreateGuidelineCommand aCommand) {
        final var aTitle = aCommand.title();
        final var aDescription = aCommand.description();

        final var aGuideline = Guideline.newGuideline(aTitle, aDescription);
        aGuideline.validate(new ThrowsValidationHandler());

        return CreateGuidelineOutput.from(this.guidelineGateway.create(aGuideline));
    }
}
