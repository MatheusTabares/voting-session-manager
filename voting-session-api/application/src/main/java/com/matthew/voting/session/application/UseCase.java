package com.matthew.voting.session.application;

import com.matthew.voting.session.domain.guideline.Guideline;

public abstract class UseCase <IN, OUT> {

    public abstract OUT execute(IN anIn);
}
