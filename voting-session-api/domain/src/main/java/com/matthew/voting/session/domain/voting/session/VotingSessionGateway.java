package com.matthew.voting.session.domain.voting.session;

import com.matthew.voting.session.domain.guideline.Guideline;

import java.util.Optional;

public interface VotingSessionGateway {

    Optional<VotingSession> findByCpf(Integer cpf);
}
