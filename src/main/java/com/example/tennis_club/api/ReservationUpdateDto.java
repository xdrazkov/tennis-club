package com.example.tennis_club.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Schema(name = "ReservationCreateDTO", description = "Represents a reservation")
public class ReservationUpdateDto {

    @Schema(description = "Court ID", example = "1")
    private Long courtId;

    @Schema(description = "Date and time of start of reservation", example = "2024-06-15T10:00:00.000Z")
    private LocalDateTime dateFrom;

    @Schema(description = "Date and time of end of reservation", example = "2024-06-15T12:00:00.000Z")
    private LocalDateTime dateTo;

    @Schema(description = "Is doubles game", example = "true")
    private Boolean isDoubles;
}
