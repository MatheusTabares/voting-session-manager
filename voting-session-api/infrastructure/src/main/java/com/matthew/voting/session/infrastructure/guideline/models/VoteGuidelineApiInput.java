package com.matthew.voting.session.infrastructure.guideline.models;

public record VoteGuidelineApiInput(

        String cpf,
        boolean upvote
) {
}
