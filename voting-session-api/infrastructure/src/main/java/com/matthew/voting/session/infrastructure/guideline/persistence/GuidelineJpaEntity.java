package com.matthew.voting.session.infrastructure.guideline.persistence;

import com.matthew.voting.session.domain.guideline.Guideline;
import com.matthew.voting.session.domain.guideline.GuidelineID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.Locale;

@Entity
@Table(name = "guideline")
public class GuidelineJpaEntity {

    @Id
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = 4000, nullable = false)
    private String description;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @Column(name = "deleted_at", columnDefinition = "DATETIME(6)")
    private Instant deletedAt;

    public static GuidelineJpaEntity from (final Guideline aGuideline) {
        return new GuidelineJpaEntity(
                aGuideline.getId().getValue(),
                aGuideline.getTitle(),
                aGuideline.getDescription(),
                aGuideline.getCreatedAt(),
                aGuideline.getUpdatedAt(),
                aGuideline.getDeletedAt()
        );
    }

    public Guideline toAggregate() {
        return Guideline.with(
                GuidelineID.from(getId()),
                getTitle(),
                getDescription(),
                getCreatedAt(),
                getUpdatedAt(),
                getDeletedAt()
        );
    }

    private GuidelineJpaEntity() {}

    private GuidelineJpaEntity(
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
    }
}