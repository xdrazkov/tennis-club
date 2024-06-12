package com.example.tennis_club.api;

import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter @Setter
@Schema(name = "CourtCreateDTO", description = "Represents a court")
public class CourtCreateDto {

    @Schema(description = "Court ID", example = "1")
    private Long id;

    @Schema(description = "Court name", example = "Court 1")
    private String name;

    @Schema(description = "ID of surface type of the court", example = "1")
    private Long surfaceTypeId;
}
