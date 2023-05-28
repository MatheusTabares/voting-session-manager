package com.matthew.voting.session.application.guideline.create;

import com.matthew.voting.session.domain.guideline.Guideline;
import com.matthew.voting.session.domain.guideline.GuidelineID;

public record CreateGuidelineOutput(
        String id
) {

    public static CreateGuidelineOutput from(final Guideline aGuideline) {
        return new CreateGuidelineOutput(aGuideline.getId().getValue());
    }
}
