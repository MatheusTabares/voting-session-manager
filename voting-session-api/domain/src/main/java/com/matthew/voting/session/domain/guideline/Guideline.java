package com.matthew.voting.session.domain.guideline;

import java.time.Instant;
import java.util.UUID;

public class Guideline {

    private String id;
    private String title;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Guideline(
            final String id,
            final String title,
            final String description,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static Guideline newGuideline(
            final String title, final String description) {
        final var id = UUID.randomUUID().toString();
        final var now = Instant.now();
        return new Guideline(id, title, description, now, now, null);
    }

    public String getId() {
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
