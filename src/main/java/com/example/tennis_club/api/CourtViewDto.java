package com.example.tennis_club.api;

import com.example.tennis_club.data.model.Reservation;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Getter @Setter
@Schema(name = "CourtViewDTO", description = "Represents a court")
public class CourtViewDto {

    @Schema(description = "Court ID", example = "1")
    private Long id;

    @Schema(description = "Court name", example = "Court 1")
    private String name;

    // TODO add surfaceTypeViewDto
    @Schema(description = "ID of surface type of the court", example = "1")
    private Long surfaceType;

    // TODO add reservationViewDto
    private List<Long> reservations;
}
