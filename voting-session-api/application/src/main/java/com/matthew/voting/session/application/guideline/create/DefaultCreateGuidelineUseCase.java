package com.matthew.voting.session.application.guideline.create;

import com.matthew.voting.session.domain.guideline.Guideline;
import com.matthew.voting.session.domain.guideline.GuidelineGateway;
import com.matthew.voting.session.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;

public class DefaultCreateGuidelineUseCase extends CreateGuidelineUseCase{

    private final GuidelineGateway guidelineGateway;

    public DefaultCreateGuidelineUseCase(final GuidelineGateway guidelineGateway) {
        this.guidelineGateway = Objects.requireNonNull(guidelineGateway);
    }

    @Override
    public Either<Notification, CreateGuidelineOutput> execute(final CreateGuidelineCommand aCommand) {
        final var aTitle = aCommand.title();
        final var aDescription = aCommand.description();

        final var notification = Notification.create();

        final var aGuideline = Guideline.newGuideline(aTitle, aDescription);
        aGuideline.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(aGuideline);
    }

    private Either<Notification, CreateGuidelineOutput> create(final Guideline aGuideline) {
        return API.Try(() -> this.guidelineGateway.save(aGuideline))
                .toEither()
                .bimap(Notification::create, CreateGuidelineOutput::from);
    }
}
