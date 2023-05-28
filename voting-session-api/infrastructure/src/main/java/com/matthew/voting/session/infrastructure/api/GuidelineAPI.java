package com.matthew.voting.session.infrastructure.api;

import com.matthew.voting.session.infrastructure.guideline.models.CreateGuidelineApiInput;
import com.matthew.voting.session.infrastructure.guideline.models.OpenGuidelineApiInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/v1/guidelines")
@Tag(name = "Guidelines")
public interface GuidelineAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new guideline")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    ResponseEntity<?> createGuideline(@RequestBody CreateGuidelineApiInput input);

    @PutMapping(
            path = "/{guidelineId}/open",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Open voting session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Opened successfully"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    ResponseEntity<?> openGuideline(@PathVariable String guidelineId, @RequestBody OpenGuidelineApiInput input);
}
