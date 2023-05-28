package com.matthew.voting.session.infrastructure.configuration.usecases;

import com.matthew.voting.session.application.guideline.create.CreateGuidelineUseCase;
import com.matthew.voting.session.application.guideline.create.DefaultCreateGuidelineUseCase;
import com.matthew.voting.session.domain.guideline.GuidelineGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GuidelineUseCaseConfig {

    private final GuidelineGateway gateway;

    public GuidelineUseCaseConfig(final GuidelineGateway gateway) {
        this.gateway = gateway;
    }

    @Bean
    public CreateGuidelineUseCase createGuidelineUseCase(final GuidelineGateway gateway) {
        return new DefaultCreateGuidelineUseCase(gateway);
    }
}
