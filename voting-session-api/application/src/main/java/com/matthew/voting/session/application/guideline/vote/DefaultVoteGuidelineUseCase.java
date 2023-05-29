package com.matthew.voting.session.application.guideline.vote;

import com.matthew.voting.session.domain.exceptions.DomainException;
import com.matthew.voting.session.domain.guideline.Guideline;
import com.matthew.voting.session.domain.guideline.GuidelineGateway;
import com.matthew.voting.session.domain.validation.Error;
import com.matthew.voting.session.domain.voting.session.VotingSession;
import com.matthew.voting.session.domain.voting.session.VotingSessionID;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class DefaultVoteGuidelineUseCase extends VoteGuidelineUseCase {

    private final GuidelineGateway guidelineGateway;

    public DefaultVoteGuidelineUseCase(final GuidelineGateway guidelineGateway) {
        this.guidelineGateway = Objects.requireNonNull(guidelineGateway);
    }

    @Override
    public void execute(VoteGuidelineCommand aCommand) {
        final var guidelineId = aCommand.guidelineId();
        final var cpf = aCommand.cpf();
        final var upvote = aCommand.upvote();

        final var aGuideline = this.guidelineGateway.findById(guidelineId)
                .orElseThrow(notFound(guidelineId));
        final var votes = aGuideline.getVotes();

        checkVotingSession(cpf, aGuideline, votes);
        votes.add(VotingSession.newVote(guidelineId, cpf, upvote));

        this.guidelineGateway.save(aGuideline);
    }

    private static void checkVotingSession(
            final String cpf,
            final Guideline aGuideline,
            final Set<VotingSession> votes) {

        final var now = Instant.now();
        final var endSession = aGuideline.getEndSession();

        if(now.isAfter(endSession)) {
            throw DomainException.with(new Error("Voting Session closed!"));
        }
        if(cpf == null || cpf.trim().length() != 11) {
            throw DomainException.with(new Error("Invalid cpf!"));
        }
        if (votes.stream().filter(vote -> cpf.equals(vote.getCpf())).findAny().isPresent()){
            throw DomainException.with(new Error("User already vote!"));
        }
    }

    private static Supplier<DomainException> notFound(String guidelineId) {
        return () -> DomainException.with(new Error("Guideline with ID %s was not found".formatted(guidelineId)));
    }
}
