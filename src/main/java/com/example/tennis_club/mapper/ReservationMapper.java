package com.example.tennis_club.mapper;

import com.example.tennis_club.api.ReservationCreateDto;
import com.example.tennis_club.api.ReservationSimpleViewDto;
import com.example.tennis_club.data.model.Reservation;
import com.example.tennis_club.service.CourtService;
import com.example.tennis_club.service.ReservationService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ReservationService.class, CourtService.class})
public interface ReservationMapper {

    @Mapping(source="courtId", target="court")
    Reservation mapFromCreate(ReservationCreateDto reservation);

    ReservationSimpleViewDto mapToSimpleViewDto(Reservation reservation);
}
