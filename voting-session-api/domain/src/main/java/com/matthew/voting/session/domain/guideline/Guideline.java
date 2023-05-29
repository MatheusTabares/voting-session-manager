package com.matthew.voting.session.domain.guideline;

import com.matthew.voting.session.domain.AggregateRoot;
import com.matthew.voting.session.domain.exceptions.DomainException;
import com.matthew.voting.session.domain.validation.Error;
import com.matthew.voting.session.domain.validation.ValidationHandler;
import com.matthew.voting.session.domain.voting.session.VotingSession;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Guideline extends AggregateRoot<GuidelineID> {

    private String title;
    private String description;
    private Instant startSession;
    private Instant endSession;
    private Set<VotingSession> votes;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Guideline(
            final GuidelineID anId,
            final String aTitle,
            final String aDescription,
            final Instant startSession,
            final Instant endSession,
            final Set<VotingSession> aVotes,
            final Instant aCreatedAt,
            final Instant aUpdatedAt,
            final Instant aDeletedAt) {
        super(anId);
        this.title = aTitle;
        this.description = aDescription;
        this.startSession = startSession;
        this.endSession = endSession;
        this.votes = aVotes;
        this.createdAt = Objects.requireNonNull(aCreatedAt, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(aUpdatedAt, "'updatedAt' should not be null");;
        this.deletedAt = aDeletedAt;
    }

    public static Guideline newGuideline(
            final String title, final String description) {
        final var id = GuidelineID.unique();
        final var now = Instant.now();
        return with(id, title, description, null, null, Set.of(), now, now, null);
    }

    public static Guideline openSession(final Guideline aGuideline, final Integer duration) {
        final var now = Instant.now();

        final var anId = aGuideline.id;
        final var aTitle = aGuideline.title;
        final var aDescription = aGuideline.description;
        final var aCreatedAt = aGuideline.createdAt;
        final var endSession = now.plusSeconds(duration);

        return with(anId, aTitle, aDescription, now, endSession, Set.of(), aCreatedAt, now, null);
    }

    public static Guideline with(
            final GuidelineID anId,
            final String aTitle,
            final String aDescription,
            final Instant startSession,
            final Instant endSession,
            final Set<VotingSession> aVotes,
            final Instant aCreatedAt,
            final Instant anUpdatedAt,
            final Instant aDeletedAt) {
        return new Guideline(
                anId,
                aTitle,
                aDescription,
                startSession,
                endSession,
                aVotes,
                aCreatedAt,
                anUpdatedAt,
                aDeletedAt);
    }

    @Override
    public void validate(ValidationHandler handler) {
        new GuidelineValidator(this, handler).validate();
    }

    public GuidelineID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Instant getStartSession() {
        return startSession;
    }

    public Instant getEndSession() {
        return endSession;
    }

    public Set<VotingSession> getVotes() {
        return votes;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }
}
