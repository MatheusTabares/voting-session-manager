package com.matthew.voting.session.infrastructure.guideline.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateGuidelineApiInput(
        String title,
        String description
) {
}
