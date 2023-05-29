package com.matthew.voting.session.infrastructure.guideline.persistence;

import com.matthew.voting.session.domain.guideline.Guideline;
import com.matthew.voting.session.domain.guideline.GuidelineID;
import com.matthew.voting.session.domain.voting.session.VotingSession;
import com.matthew.voting.session.domain.voting.session.VotingSessionID;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "guideline")
public class GuidelineJpaEntity {

    @Id
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = 4000, nullable = false)
    private String description;

    @Column(name = "start_session")
    private Instant startSession;

    @Column(name = "end_session")
    private Instant endSession;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @Column(name = "deleted_at", columnDefinition = "DATETIME(6)")
    private Instant deletedAt;

    @OneToMany(mappedBy="guideline", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<VotingSessionJpaEntity> votes = Set.of();

    public static GuidelineJpaEntity newGuidelineJpaEntity(final String guidelineId) {
        return new GuidelineJpaEntity(guidelineId);
    }
    public static GuidelineJpaEntity from (final Guideline aGuideline) {
        return new GuidelineJpaEntity(
                aGuideline.getId().getValue(),
                aGuideline.getTitle(),
                aGuideline.getDescription(),
                aGuideline.getStartSession(),
                aGuideline.getEndSession(),
                aGuideline.getVotes(),
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
                getStartSession(),
                getEndSession(),
                toAggregateVotes(),
                getCreatedAt(),
                getUpdatedAt(),
                getDeletedAt()
        );
    }

    private Set<VotingSession> toAggregateVotes() {
        return getVotes().stream().map(vote -> VotingSession.with(
                VotingSessionID.from(vote.getId()),
                        getId(),
                        vote.getCpf(),
                        vote.isUpvote()))
                .collect(Collectors.toSet());
    }

    private GuidelineJpaEntity() {}

    private GuidelineJpaEntity(
            final String id,
            final String title,
            final String description,
            final Instant startSession,
            final Instant endSession,
            final Set<VotingSession> votes,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startSession = startSession;
        this.endSession = endSession;
        this.votes = votes.stream().map(VotingSessionJpaEntity::newVotingSession).collect(Collectors.toSet());
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    private GuidelineJpaEntity(final String id) {
        this.id = id;
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

    public Instant getStartSession() {
        return startSession;
    }

    public void setStartSession(Instant startSession) {
        this.startSession = startSession;
    }

    public Instant getEndSession() {
        return endSession;
    }

    public void setEndSession(Instant endSession) {
        this.endSession = endSession;
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

    public Set<VotingSessionJpaEntity> getVotes() {
        return votes;
    }

    public void setVotes(Set<VotingSessionJpaEntity> votes) {
        this.votes = votes;
    }
}
