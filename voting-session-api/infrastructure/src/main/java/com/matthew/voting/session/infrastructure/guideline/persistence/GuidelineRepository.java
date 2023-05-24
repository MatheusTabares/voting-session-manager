package com.matthew.voting.session.infrastructure.guideline.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GuidelineRepository extends JpaRepository<GuidelineJpaEntity, String> {
}
