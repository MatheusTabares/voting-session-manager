package com.matthew.voting.session.application.guideline.openSession;

import com.matthew.voting.session.application.guideline.create.CreateGuidelineCommand;

public record OpenSessionGuidelineCommand(
        String guidelineId,
        String duration
) {

    public static OpenSessionGuidelineCommand with(
            final String aGuidelineId,
            final String aDuration
    ) {
        return new OpenSessionGuidelineCommand(aGuidelineId, aDuration);
    }
}
