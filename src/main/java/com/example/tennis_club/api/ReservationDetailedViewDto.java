package com.example.tennis_club.api;

import com.example.tennis_club.data.model.Court;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(name = "ReservationSimpleViewDTO", description = "Represents a reservation")
public class ReservationDetailedViewDto {
    @Schema(description = "Reservation ID", example = "1")
    private Long id;

    @Schema(description = "Court")
    private CourtViewDto court;

    @Schema(description = "Date and time of start of reservation", example = "2021-12-01 12:00:00")
    private LocalDateTime dateFrom;

    @Schema(description = "Date and time of end of reservation", example = "2021-12-01 13:00:00")
    private LocalDateTime dateTo;

    @Schema(description = "Is doubles game", example = "true")
    private boolean isDoubles;

    @Schema(description = "Cost of the reservation", example = "100.50")
    private float cost;

    @Schema(description = "Date and time of creation", example = "2021-12-01 12:00:00")
    private LocalDateTime created;
}
