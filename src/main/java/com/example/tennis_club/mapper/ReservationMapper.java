package com.example.tennis_club.mapper;

import com.example.tennis_club.api.ReservationCreateDto;
import com.example.tennis_club.api.ReservationDetailedViewDto;
import com.example.tennis_club.api.ReservationSimpleViewDto;
import com.example.tennis_club.api.ReservationUpdateDto;
import com.example.tennis_club.data.model.Reservation;
import com.example.tennis_club.service.CourtService;
import com.example.tennis_club.service.ReservationService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ReservationService.class, CourtService.class})
public interface ReservationMapper {

    @Mapping(source="courtId", target="court")
    Reservation mapFromCreate(ReservationCreateDto reservation);

    @Mapping(source="courtId", target="court", defaultExpression = "java(null)")
    Reservation mapFromUpdate(ReservationUpdateDto reservation);

    @Mapping(target="courtId", expression = "java(reservation.getCourt().getId())")
    @Mapping(target="created", source="created")
    ReservationSimpleViewDto mapToSimpleViewDto(Reservation reservation);

    @Mapping(target="cost", expression = "java(reservation.getCost() / 100.0)")
    ReservationDetailedViewDto mapToDetailedViewDto(Reservation reservation);

    List<ReservationSimpleViewDto> mapToList(List<Reservation> courts);

    List<ReservationDetailedViewDto> mapToDetailedViewList(List<Reservation> byPhoneNumber);
}
