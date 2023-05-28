package com.matthew.voting.session.application.guideline.create;

import com.matthew.voting.session.application.UseCase;
import com.matthew.voting.session.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateGuidelineUseCase
    extends UseCase<CreateGuidelineCommand, Either<Notification, CreateGuidelineOutput>> {
}
