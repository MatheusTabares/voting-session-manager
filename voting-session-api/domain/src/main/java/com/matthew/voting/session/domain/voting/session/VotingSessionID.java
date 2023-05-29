package com.matthew.voting.session.domain.voting.session;

import com.matthew.voting.session.domain.Identifier;
import com.matthew.voting.session.domain.guideline.GuidelineID;

import java.util.Objects;
import java.util.UUID;

public class VotingSessionID extends Identifier {

    private final String value;

    private VotingSessionID(final String value) {
        Objects.requireNonNull(value, "'value' should not be null");
        this.value = value;
    }

    public static VotingSessionID unique() {
        return VotingSessionID.from(UUID.randomUUID());
    }

    public static VotingSessionID from(final String anId) {
        return new VotingSessionID(anId);
    }

    public static VotingSessionID from (final UUID anId) {
        return new VotingSessionID(anId.toString().toLowerCase());
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final GuidelineID that = (GuidelineID) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
