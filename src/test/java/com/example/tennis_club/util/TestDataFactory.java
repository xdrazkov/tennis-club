package com.example.tennis_club.util;

import com.example.tennis_club.api.CourtCreateDto;
import com.example.tennis_club.api.CourtViewDto;
import com.example.tennis_club.api.ReservationCreateDto;
import com.example.tennis_club.api.ReservationDetailedViewDto;
import com.example.tennis_club.api.ReservationSimpleViewDto;
import com.example.tennis_club.api.ReservationUpdateDto;
import com.example.tennis_club.data.model.Court;
import com.example.tennis_club.data.model.Customer;
import com.example.tennis_club.data.model.Reservation;
import com.example.tennis_club.data.model.SurfaceType;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestDataFactory {

    private static final TestEntityMapper testEntityMapper = Mappers.getMapper(TestEntityMapper.class);

    public static SurfaceType surfaceType = createSurfaceType();
    public static SurfaceType surfaceType2 = createSurfaceType();

    public static Court court = createCourt();
    public static CourtViewDto courtViewDto = testEntityMapper.mapToDto(court);
    public static List<CourtViewDto> courtViewDtoList = List.of(courtViewDto);
    public static CourtCreateDto courtCreateDto = testEntityMapper.mapToCreateCourt(court);

    public static Customer customer = createCustomer();

    public static Reservation reservation = createReservation();
    public static ReservationSimpleViewDto reservationSimpleViewDto = testEntityMapper.mapToSimpleViewDto(reservation);
    public static List<ReservationSimpleViewDto> reservationSimpleViewDtoList = List.of(reservationSimpleViewDto);
    public static ReservationDetailedViewDto reservationDetailedViewDto = testEntityMapper.mapToDetailedViewDto(reservation);
    public static List<ReservationDetailedViewDto> reservationDetailedViewDtoList = List.of(reservationDetailedViewDto);
    public static ReservationCreateDto reservationCreateDto = testEntityMapper.mapToCreateReservation(reservation);
    public static ReservationUpdateDto reservationUpdateDto = testEntityMapper.mapToUpdateReservation(reservation);

    private static SurfaceType createSurfaceType() {
        return new SurfaceType(
                1L,
                "Grass",
                10,
                new ArrayList<>()
        );
    }

    private static Court createCourt() {
        return new Court(
                1L,
                "Court 1",
                surfaceType,
                new ArrayList<>()
        );
    }

    private static Customer createCustomer() {
        return new Customer(
                1L,
                "Arthur Dent",
                "123456789",
                new ArrayList<>()
        );
    }

    private static Reservation createReservation() {
        return new Reservation(
                1L,
                court,
                customer,
                LocalDateTime.parse("2021-12-12T12:00"),
                LocalDateTime.parse("2021-12-12T13:00"),
                true,
                100,
                LocalDateTime.parse("2021-12-12T11:00")
        );
    }
}
