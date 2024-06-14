package com.example.tennis_club.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Schema(name = "ReservationCreateDTO", description = "Represents a reservation")
public class ReservationCreateDto {

    @Schema(description = "Court ID", example = "1")
    private Long courtId;

    @Schema(description = "Date and time of start of reservation", example = "2021-12-01 12:00:00")
    private LocalDateTime dateFrom;

    @Schema(description = "Date and time of end of reservation", example = "2021-12-01 13:00:00")
    private LocalDateTime dateTo;

    @Schema(description = "Is doubles game", example = "true")
    private Boolean isDoubles;

    @Schema(description = "Phone number of the customer", example = "2267709")
    private String customerPhone;

    @Schema(description = "Name of the customer", example = "Arthur Dent")
    private String customerName;
}
