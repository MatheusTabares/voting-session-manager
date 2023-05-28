package com.matthew.voting.session.domain.guideline;

import com.matthew.voting.session.domain.AggregateRoot;
import com.matthew.voting.session.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Guideline extends AggregateRoot<GuidelineID> {

    private String title;
    private String description;

    private boolean openSession;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Guideline(
            final GuidelineID anId,
            final String aTitle,
            final String aDescription,
            final boolean openSession,
            final Instant aCreatedAt,
            final Instant aUpdatedAt,
            final Instant aDeletedAt) {
        super(anId);
        this.title = aTitle;
        this.description = aDescription;
        this.openSession = openSession;
        this.createdAt = Objects.requireNonNull(aCreatedAt, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(aUpdatedAt, "'updatedAt' should not be null");;
        this.deletedAt = aDeletedAt;
    }

    public static Guideline newGuideline(
            final String title, final String description) {
        final var id = GuidelineID.unique();
        final var now = Instant.now();
        return with(id, title, description, false, now, now, null);
    }

    public static Guideline openSession(final Guideline aGuideline) {
        final var anId = aGuideline.id;
        final var aTitle = aGuideline.title;
        final var aDescription = aGuideline.description;
        final var aCreatedAt = aGuideline.createdAt;
        final var anUpdatedAt = Instant.now();
        return with(anId, aTitle, aDescription, true, aCreatedAt, anUpdatedAt, null);
    }

    public static Guideline with(
            final GuidelineID anId,
            final String aTitle,
            final String aDescription,
            final boolean openSession,
            final Instant aCreatedAt,
            final Instant anUpdatedAt,
            final Instant aDeletedAt) {
        return new Guideline(
                anId,
                aTitle,
                aDescription,
                openSession,
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

    public boolean isOpenSession() { return openSession; }

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
