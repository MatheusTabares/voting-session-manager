package com.matthew.voting.session.domain.guideline;

public class AggregateRoot<ID extends Identifier> extends Entity<ID> {
    protected AggregateRoot(final ID id) {
        super(id);
    }
}
