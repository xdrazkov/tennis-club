package com.example.tennis_club.mapper;

import com.example.tennis_club.api.ReservationCreateDto;
import com.example.tennis_club.api.ReservationSimpleViewDto;
import com.example.tennis_club.data.model.Reservation;
import com.example.tennis_club.service.ReservationService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ReservationService.class})
public interface ReservationMapper {

    Reservation mapFromCreate(ReservationCreateDto reservation);

    ReservationSimpleViewDto mapToSimpleViewDto(Reservation reservation);
}
