package com.matthew.voting.session.domain.guideline;

import com.matthew.voting.session.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class GuidelineID extends Identifier {

    private final String value;

    private GuidelineID(final String value) {
        Objects.requireNonNull(value, "'value' should not be null");
        this.value = value;
    }

    public static GuidelineID unique() {
        return GuidelineID.from(UUID.randomUUID());
    }

    public static GuidelineID from(final String anId) {
        return new GuidelineID(anId);
    }

    public static GuidelineID from (final UUID anId) {
        return new GuidelineID(anId.toString().toLowerCase());
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
