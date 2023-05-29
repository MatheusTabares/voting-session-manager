package com.matthew.voting.session.application.guideline.openSession;

import com.matthew.voting.session.application.guideline.create.CreateGuidelineCommand;

import java.time.temporal.ChronoUnit;

public record OpenSessionGuidelineCommand(
        String guidelineId,
        Integer duration
) {

    public static OpenSessionGuidelineCommand with(
            final String aGuidelineId,
            final Integer aDuration
    ) {
        return new OpenSessionGuidelineCommand(aGuidelineId, aDuration);
    }
}
