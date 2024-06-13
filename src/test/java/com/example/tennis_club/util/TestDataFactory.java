package com.example.tennis_club.util;

import com.example.tennis_club.api.CourtCreateDto;
import com.example.tennis_club.api.CourtViewDto;
import com.example.tennis_club.api.ReservationCreateDto;
import com.example.tennis_club.api.ReservationDetailedViewDto;
import com.example.tennis_club.api.ReservationSimpleViewDto;
import com.example.tennis_club.data.model.Court;
import com.example.tennis_club.data.model.Customer;
import com.example.tennis_club.data.model.Reservation;
import com.example.tennis_club.data.model.SurfaceType;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class TestDataFactory {

    private static final TestEntityMapper testEntityMapper = Mappers.getMapper(TestEntityMapper.class);

    public static SurfaceType surfaceType = createSurfaceType();

    public static Court court = createCourt();
    public static CourtViewDto courtViewDto = testEntityMapper.mapToDto(court);
    private static CourtCreateDto courtCreateDto = testEntityMapper.mapToCreateCourt(court);

    public static Customer customer = createCustomer();

    public static Reservation reservation = createReservation();
    private static ReservationSimpleViewDto reservationSimpleViewDto = testEntityMapper.mapToSimpleViewDto(reservation);
    private static ReservationDetailedViewDto reservationDetailedViewDto = testEntityMapper.mapToDetailedViewDto(reservation);
    private static ReservationCreateDto reservationCreateDto = testEntityMapper.mapToCreateReservation(reservation);

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
                "John",
                "Doe",
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
