package com.matthew.voting.session.domain.guideline;

import com.matthew.voting.session.domain.validation.Error;
import com.matthew.voting.session.domain.validation.ValidationHandler;
import com.matthew.voting.session.domain.validation.Validator;

public class GuidelineValidator extends Validator {


    public static final int MAX_LENGHT_TITLE = 127;
    public static final int MAX_LENGTH_DESCRIPTION = 255;
    private final Guideline guideline;
    public GuidelineValidator(final Guideline aGuideline, final ValidationHandler anHandler) {
        super(anHandler);
        this.guideline = aGuideline;
    }

    @Override
    public void validate() {
        checkTitleConstraints();
        checkDescriptionConstraints();
    }

    private void checkTitleConstraints() {
        final var title = this.guideline.getTitle();
        if (title == null) {
            this.validationHandler().append(new Error("'title' should not be null"));
            return;
        }
        if (title.isBlank()) {
            this.validationHandler().append(new Error("'title' should not be empty"));
            return;
        }
        final var length = title.trim().length();
        if (length > MAX_LENGHT_TITLE) {
            this.validationHandler().append(new Error("'title' must not exceed 127 characters"));
        }
    }

    private void checkDescriptionConstraints() {
        final var description = this.guideline.getDescription();
        if(description != null && description.length() > MAX_LENGTH_DESCRIPTION) {
            this.validationHandler().append(new Error("'description' must not exceed 255 characters"));
        }
    }
}
