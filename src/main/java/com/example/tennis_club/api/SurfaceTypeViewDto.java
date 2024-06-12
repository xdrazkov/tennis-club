package com.example.tennis_club.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Schema(name = "SurfaceTypeViewDTO", description = "Represents a court")
public class SurfaceTypeViewDto {
    @Schema(description = "Court ID", example = "1")
    private Long id;

    @Schema(description = "Surface type name", example = "Grass")
    private String name;

    @Schema(description = "Price per minute in cents", example = "10")
    private int pricePerMinute;
}
