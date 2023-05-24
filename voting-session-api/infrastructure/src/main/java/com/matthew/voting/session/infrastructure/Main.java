package com.matthew.voting.session.infrastructure;

import com.matthew.voting.session.domain.guideline.Guideline;
import com.matthew.voting.session.infrastructure.configuration.WebServerConfig;
import com.matthew.voting.session.infrastructure.guideline.persistence.GuidelineJpaEntity;
import com.matthew.voting.session.infrastructure.guideline.persistence.GuidelineRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.AbstractEnvironment;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "development");
        SpringApplication.run(WebServerConfig.class, args);
    }

    @Bean
    public ApplicationRunner runner(GuidelineRepository repository) {
        return args -> {
            var all = repository.findAll();
            Guideline guideline = Guideline.newGuideline("Primeira pauta", "Descrição da primeira pauta");
            repository.saveAndFlush(GuidelineJpaEntity.from(guideline));
            repository.deleteAll();
        };
    }
}
