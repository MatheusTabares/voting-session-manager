package com.matthew.voting.session.infrastructure.guideline;

import com.matthew.voting.session.domain.guideline.Guideline;
import com.matthew.voting.session.domain.guideline.GuidelineGateway;
import com.matthew.voting.session.infrastructure.guideline.persistence.GuidelineJpaEntity;
import com.matthew.voting.session.infrastructure.guideline.persistence.GuidelineRepository;
import org.springframework.stereotype.Service;

@Service
public class GuidelineMySQLGateway implements GuidelineGateway {

    private final GuidelineRepository repository;

    public GuidelineMySQLGateway(final GuidelineRepository repository) {
        this.repository = repository;
    }

    @Override
    public Guideline create(final Guideline aGuideline) {
        return repository.save(GuidelineJpaEntity.from(aGuideline)).toAggregate();
    }
}
