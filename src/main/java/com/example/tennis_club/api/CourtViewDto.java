package com.example.tennis_club.api;

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

    @Schema(description = "Surface type of the court")
    private SurfaceTypeViewDto surfaceType;
}
