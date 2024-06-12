package com.example.tennis_club.rest;

import com.example.tennis_club.api.CourtCreateDto;
import com.example.tennis_club.api.CourtViewDto;
import com.example.tennis_club.facade.CourtFacade;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@OpenAPIDefinition(
        info = @Info(
                title = "Court Service",
                version = "1.0",
                description = """
                        Simple service for managing courts. The API has operations to:
                        "   - Create court
                        """
        )
)

@Tag(name = "Court controller", description = "Controller for managing courts")
@RequestMapping("/api/courts")
public class CourtRestController {
    private final CourtFacade courtFacade;

    @Autowired
    public CourtRestController(CourtFacade courtFacade) {
        this.courtFacade = courtFacade;
    }

    /**
     * REST method creating a new court
     */
    @Operation(
            summary = "Create court",
            description = "Creates a new court.",
            responses = {
                    @ApiResponse(responseCode = "201"),
            })
    @PostMapping("/")
    public ResponseEntity<CourtViewDto> createRecord(@RequestBody CourtCreateDto court) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(courtFacade.createRecord(court));
    }

    /**
     * REST method returning all courts
     */
    @Operation(
            summary = "Get all courts",
            description = "Returns all courts.",
            responses = {
                    @ApiResponse(responseCode = "200")
            })
    @GetMapping("/")
    public ResponseEntity<List<CourtViewDto>> findAll() {
        return ResponseEntity.ok(courtFacade.findAll());
    }

    /**
     * REST method returning court by its id
     */
    @Operation(
            summary = "Find court by id",
            description = "Looks up a court by its id.",
            responses = {
                    @ApiResponse(responseCode = "200"),
            })
    @GetMapping("/{id}")
    public ResponseEntity<CourtViewDto> findById(@PathVariable("id") Long courtId) {
        return ResponseEntity.ok(courtFacade.findById(courtId));
    }
}
