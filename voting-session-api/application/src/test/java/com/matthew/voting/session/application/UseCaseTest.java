package com.matthew.voting.session.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UseCaseTest {

    @Test
    public void testExecuteUseCase() {
        Assertions.assertNotNull(new UseCase());
        //Assertions.assertNotNull(new UseCase().execute());
    }

}
