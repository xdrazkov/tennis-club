package com.example.tennis_club.rest;

import com.example.tennis_club.api.ReservationCreateDto;
import com.example.tennis_club.api.ReservationSimpleViewDto;
import com.example.tennis_club.facade.ReservationFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<ReservationSimpleViewDto> create(@RequestBody ReservationCreateDto reservation) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reservationFacade.create(reservation));
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
}
