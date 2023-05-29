package com.matthew.voting.session.infrastructure.guideline.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VotingSessionRepository extends JpaRepository<VotingSessionJpaEntity, String> {
}
