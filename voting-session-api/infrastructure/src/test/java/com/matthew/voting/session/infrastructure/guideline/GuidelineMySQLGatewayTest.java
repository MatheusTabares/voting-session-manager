package com.matthew.voting.session.infrastructure.guideline;

import com.matthew.voting.session.infrastructure.MySQLGatewayTest;
import com.matthew.voting.session.infrastructure.guideline.persistence.GuidelineRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@MySQLGatewayTest
public class GuidelineMySQLGatewayTest {

    @Autowired
    private GuidelineMySQLGateway guidelineGateway;

    @Autowired
    private GuidelineRepository guidelineRepository;

    @Test
    public void testInjectedDependencies() {
        Assertions.assertNotNull(guidelineGateway);
        Assertions.assertNotNull(guidelineRepository);
    }

}
