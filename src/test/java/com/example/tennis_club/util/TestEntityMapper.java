package com.example.tennis_club.util;

import com.example.tennis_club.api.CourtCreateDto;
import com.example.tennis_club.api.CourtViewDto;
import com.example.tennis_club.api.ReservationCreateDto;
import com.example.tennis_club.api.ReservationDetailedViewDto;
import com.example.tennis_club.api.ReservationSimpleViewDto;
import com.example.tennis_club.api.ReservationUpdateDto;
import com.example.tennis_club.data.model.Court;
import com.example.tennis_club.data.model.Reservation;
import com.example.tennis_club.mapper.ReservationMapper;
import com.example.tennis_club.service.CourtService;
import com.example.tennis_club.service.ReservationService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CourtService.class, ReservationService.class})
public interface TestEntityMapper {
    @Mapping(source="surfaceTypeId", target="surfaceType", defaultExpression = "java(null)")
    Court mapFromCreate(CourtCreateDto court);

    @Mapping(source="surfaceType", target="surfaceType")
    CourtViewDto mapToDto(Court court);

    List<CourtViewDto> mapToListCourts(List<Court> courts);

    @Mapping(source="courtId", target="court")
    Reservation mapFromCreate(ReservationCreateDto reservation);

    @Mapping(source="courtId", target="court", defaultExpression = "java(null)")
    Reservation mapFromUpdate(ReservationUpdateDto reservation);

    @Mapping(target="courtId", expression = "java(reservation.getCourt().getId())")
    ReservationSimpleViewDto mapToSimpleViewDto(Reservation reservation);

    @Mapping(target="cost", expression = "java(reservation.getCost() / 100)")
    ReservationDetailedViewDto mapToDetailedViewDto(Reservation reservation);

    List<ReservationSimpleViewDto> mapToListReservations(List<Reservation> courts);

    List<ReservationDetailedViewDto> mapToDetailedViewList(List<Reservation> byPhoneNumber);

    CourtCreateDto mapToCreateCourt(Court court);
    ReservationCreateDto mapToCreateReservation(Reservation reservation);
}
