package com.matthew.voting.session.domain.guideline;

import com.matthew.voting.session.domain.AggregateRoot;

import java.time.Instant;
import java.util.UUID;

public class Guideline extends AggregateRoot<GuidelineID> {

    private String title;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Guideline(
            final GuidelineID anId,
            final String aTitle,
            final String aDescription,
            final Instant aCreatedAt,
            final Instant aUpdatedAt,
            final Instant aDeletedAt) {
        super(anId);
        this.title = aTitle;
        this.description = aDescription;
        this.createdAt = aCreatedAt;
        this.updatedAt = aUpdatedAt;
        this.deletedAt = aDeletedAt;
    }

    public static Guideline newGuideline(
            final String title, final String description) {
        final var id = GuidelineID.unique();
        final var now = Instant.now();
        return new Guideline(id, title, description, now, now, null);
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
