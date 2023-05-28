package com.matthew.voting.session.domain.guideline;

import java.util.Optional;

public interface GuidelineGateway {

    Guideline save(Guideline aGuideline);

    Optional<Guideline> findById(String guidelineId);
}
