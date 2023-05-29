package com.matthew.voting.session.infrastructure.guideline;

import com.matthew.voting.session.domain.guideline.Guideline;
import com.matthew.voting.session.domain.guideline.GuidelineGateway;
import com.matthew.voting.session.infrastructure.guideline.persistence.GuidelineJpaEntity;
import com.matthew.voting.session.infrastructure.guideline.persistence.GuidelineRepository;
import com.matthew.voting.session.infrastructure.guideline.persistence.VotingSessionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GuidelineMySQLGateway implements GuidelineGateway {

    private final GuidelineRepository repository;

    private final VotingSessionRepository votingSessionRepository;

    public GuidelineMySQLGateway(
            final GuidelineRepository repository,
            final VotingSessionRepository votingSessionRepository) {
        this.repository = repository;
        this.votingSessionRepository = votingSessionRepository;
    }

    @Override
    public Guideline save(final Guideline aGuideline) {
        return repository.save(GuidelineJpaEntity.from(aGuideline)).toAggregate();
    }

    @Override
    public Optional<Guideline> findById(String guidelineId) {
        final var guidelineOpt = repository.findById(guidelineId);
        return guidelineOpt.isPresent() ? Optional.of(guidelineOpt.get().toAggregate()) : Optional.empty();
    }
}
