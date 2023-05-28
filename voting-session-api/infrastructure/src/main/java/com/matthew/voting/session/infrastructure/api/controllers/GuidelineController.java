package com.matthew.voting.session.infrastructure.api.controllers;

import com.matthew.voting.session.infrastructure.api.GuidelineAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuidelineController implements GuidelineAPI {

    @Override
    public ResponseEntity<?> createGuideline() {
        return null;
    }

}
