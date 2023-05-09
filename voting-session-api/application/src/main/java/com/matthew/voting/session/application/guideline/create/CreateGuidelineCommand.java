package com.matthew.voting.session.application.guideline.create;

public record CreateGuidelineCommand(
        String title,
        String description
) {

    public static CreateGuidelineCommand with(
            final String aTitle,
            final String aDescription
    ) {
        return new CreateGuidelineCommand(aTitle, aDescription);
    }
}
