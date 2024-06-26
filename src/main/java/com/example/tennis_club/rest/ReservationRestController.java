package com.example.tennis_club.rest;

import com.example.tennis_club.api.ReservationCreateDto;
import com.example.tennis_club.api.ReservationDetailedViewDto;
import com.example.tennis_club.api.ReservationSimpleViewDto;
import com.example.tennis_club.api.ReservationUpdateDto;
import com.example.tennis_club.facade.ReservationFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@Tag(name = "Reservations controller", description = "Controller for managing reservations")
public class ReservationRestController {
    private final ReservationFacade reservationFacade;

    @Autowired
    public ReservationRestController(ReservationFacade reservationFacade) {
        this.reservationFacade = reservationFacade;
    }

    /**
     * REST method creating a new reservation
     */
    @Operation(
            summary = "Create reservation",
            description = "Creates a new reservation.",
            responses = {
                    @ApiResponse(responseCode = "201"),
            })
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody ReservationCreateDto reservation) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(reservationFacade.create(reservation));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid request: " + e.getMessage());
        }
    }

    /**
     * REST method returning all reservations
     */
    @Operation(
            summary = "Get all reservations",
            description = "Returns all reservations.",
            responses = {
                    @ApiResponse(responseCode = "200")
            })
    @GetMapping("/")
    public ResponseEntity<List<ReservationSimpleViewDto>> findAll() {
        return ResponseEntity.ok(reservationFacade.findAll());
    }

    /**
     * REST method updating a reservation
     */
    @Operation(
            summary = "Update reservation",
            description = """
                    Updates a reservation based on the body sent in the request.
                    Returns the updated court as its response""",
            responses = {
                    @ApiResponse(responseCode = "200"),
            })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long reservationId, @RequestBody ReservationUpdateDto reservation) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(reservationFacade.update(reservationId, reservation));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid request: " + e.getMessage());
        }
    }

    /**
     * REST method updating a reservation
     */
    @Operation(
            summary = "Update reservation",
            description = """
                    Updates a reservation based on the body sent in the request.
                    Returns the updated court as its response""",
            responses = {
                    @ApiResponse(responseCode = "200"),
            })
    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdate(@PathVariable("id") Long reservationId, @RequestBody ReservationUpdateDto reservation) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(reservationFacade.update(reservationId, reservation));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid request: " + e.getMessage());
        }
    }

    /**
     * REST method deleting a reservation
     */
    @Operation(
            summary = "Delete reservation",
            description = "Deletes a reservation based on the id sent in the request.",
            responses = {
                    @ApiResponse(responseCode = "204"),
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long reservationId) {
        reservationFacade.deleteById(reservationId);
        return ResponseEntity.noContent().build();
    }

    /**
     * REST method returning reservations by court id
     */
    @Operation(
            summary = "Find reservations by court id",
            description = "Looks up a reservations by court id.",
            responses = {
                    @ApiResponse(responseCode = "200"),
            })
    @GetMapping("/court/{courtId}")
    public ResponseEntity<List<ReservationSimpleViewDto>> findByCourtId(@PathVariable("courtId") Long courtId) {
        return ResponseEntity.ok(reservationFacade.findByCourtId(courtId));
    }

    /**
     * REST method returning reservations by phone number
     */
    @Operation(
            summary = "Find reservations by phone number",
            description = "Looks up a reservations by phone number.",
            responses = {
                    @ApiResponse(responseCode = "200"),
            })
    @GetMapping("/customer")
    public ResponseEntity<List<ReservationDetailedViewDto>> findByPhoneNumber(@RequestParam String phoneNumber,
                                                                              @RequestParam(required = false, defaultValue = "false") boolean showFutureOnly) {
        return ResponseEntity.ok(reservationFacade.findByPhoneNumber(phoneNumber, showFutureOnly));
    }
}
