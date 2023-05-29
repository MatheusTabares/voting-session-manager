package com.matthew.voting.session.domain.voting.session;

import com.matthew.voting.session.domain.AggregateRoot;

public class VotingSession extends AggregateRoot<VotingSessionID> {

    private String guidelineId;
    private String cpf;
    private boolean upvote;
    private VotingSession(
            final VotingSessionID anId,
            final String aGuidelineId,
            final String aCpf,
            final boolean anUpvote) {
        super(anId);
        this.guidelineId = aGuidelineId;
        this.cpf = aCpf;
        this.upvote = anUpvote;
    }

    public static VotingSession newVote(
            final String aGuidelineId,
            final String aCpf,
            final boolean anUpvote) {
        final var anId = VotingSessionID.unique();
        return with(anId, aGuidelineId, aCpf, anUpvote);
    }

    public static VotingSession with(
            final VotingSessionID anId,
            final String aGuidelineId,
            final String aCpf,
            final boolean anUpvote) {
        return new VotingSession(
                anId,
                aGuidelineId,
                aCpf,
                anUpvote);
    }

    public String getGuidelineId() {
        return guidelineId;
    }

    public String getCpf() {
        return cpf;
    }

    public boolean isUpvote() {
        return upvote;
    }
}
