package com.matthew.voting.session.application.guideline.vote;

public record VoteGuidelineCommand(
        String guidelineId,
        String cpf,
        boolean upvote
) {
    public static VoteGuidelineCommand with(final String guidelineId, final String aCpf, final boolean upvote) {
        return new VoteGuidelineCommand(guidelineId, aCpf, upvote);
    }
}