package com.matthew.voting.session.infrastructure.api.controllers;

import com.matthew.voting.session.application.guideline.create.CreateGuidelineCommand;
import com.matthew.voting.session.application.guideline.create.CreateGuidelineOutput;
import com.matthew.voting.session.application.guideline.create.CreateGuidelineUseCase;
import com.matthew.voting.session.application.guideline.openSession.OpenSessionGuidelineCommand;
import com.matthew.voting.session.application.guideline.openSession.OpenSessionGuidelineUseCase;
import com.matthew.voting.session.domain.validation.handler.Notification;
import com.matthew.voting.session.infrastructure.api.GuidelineAPI;
import com.matthew.voting.session.infrastructure.guideline.models.CreateGuidelineApiInput;
import com.matthew.voting.session.infrastructure.guideline.models.OpenGuidelineApiInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;
import java.util.function.Function;

@RestController
public class GuidelineController implements GuidelineAPI {

    private CreateGuidelineUseCase createGuidelineUseCase;
    private OpenSessionGuidelineUseCase openSessionGuidelineUseCase;

    public GuidelineController(
            final CreateGuidelineUseCase createGuidelineUseCase,
            final OpenSessionGuidelineUseCase openSessionGuidelineUseCase
    ) {
        this.createGuidelineUseCase = Objects.requireNonNull(createGuidelineUseCase);
        this.openSessionGuidelineUseCase = Objects.requireNonNull(openSessionGuidelineUseCase);
    }

    @Override
    public ResponseEntity<?> createGuideline(final CreateGuidelineApiInput input) {
        final var aCommand = CreateGuidelineCommand.with(input.title(), input.description());

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateGuidelineOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/guidelines/" + output.id())).body(output);

        return this.createGuidelineUseCase.execute(aCommand).fold(onError, onSuccess);
    }

    @Override
    public ResponseEntity<?> openGuideline(final String guidelineId, final OpenGuidelineApiInput input) {
        final var aCommand = OpenSessionGuidelineCommand.with(guidelineId, input.duration());
        this.openSessionGuidelineUseCase.execute(aCommand);
        return ResponseEntity.ok().build();
    }

}
