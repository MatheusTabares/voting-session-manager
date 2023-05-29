package com.matthew.voting.session.infrastructure.guideline.persistence;

import com.matthew.voting.session.domain.voting.session.VotingSession;
import com.matthew.voting.session.domain.voting.session.VotingSessionID;
import jakarta.persistence.*;

@Entity
@Table(name = "voting_session")
public class VotingSessionJpaEntity {

    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "guideline_id", nullable = false)
    private GuidelineJpaEntity guideline;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "upvote", nullable = false)
    private boolean upvote;

    private VotingSessionJpaEntity() {}

    private VotingSessionJpaEntity(
            final String anId,
            final GuidelineJpaEntity aGuideline,
            final String aCpf,
            final boolean anUpvote
    ) {
        this.id = anId;
        this.guideline = aGuideline;
        this.cpf = aCpf;
        this.upvote = anUpvote;
    }

    public static VotingSessionJpaEntity newVotingSession(final VotingSession votingSession) {
        final var anId = votingSession.getId().getValue();
        final var aCpf = votingSession.getCpf();
        final var aGuideline = votingSession.getGuidelineId();
        final var anUpvote = votingSession.isUpvote();
        return new VotingSessionJpaEntity(anId, GuidelineJpaEntity.newGuidelineJpaEntity(aGuideline), aCpf, anUpvote);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GuidelineJpaEntity getGuideline() {
        return guideline;
    }

    public void setGuideline(GuidelineJpaEntity guideline) {
        this.guideline = guideline;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean isUpvote() {
        return upvote;
    }

    public void setUpvote(boolean upvote) {
        this.upvote = upvote;
    }
}
