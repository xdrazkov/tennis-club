package com.example.tennis_club.mapper;

import com.example.tennis_club.api.CourtCreateDto;
import com.example.tennis_club.api.CourtViewDto;
import com.example.tennis_club.data.model.Court;
import com.example.tennis_club.service.CourtService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CourtService.class})
public interface CourtMapper {

    Court mapFromCreate(CourtCreateDto court);

    CourtViewDto mapToDto(Court court);
}
