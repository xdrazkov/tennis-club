package com.example.tennis_club.facade;

import com.example.tennis_club.api.ReservationDetailedViewDto;
import com.example.tennis_club.api.ReservationSimpleViewDto;
import com.example.tennis_club.data.model.Reservation;
import com.example.tennis_club.mapper.ReservationMapper;
import com.example.tennis_club.service.ReservationService;
import com.example.tennis_club.util.TestDataFactory;
import com.example.tennis_club.util.TestEntityMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ReservationFacadeTest {
    private final TestEntityMapper testEntityMapper = Mappers.getMapper(TestEntityMapper.class);

    @Mock
    private ReservationService reservationService;

    @Mock
    private ReservationMapper reservationMapper;

    @InjectMocks
    private ReservationFacade reservationFacade;

    @Test
    void findAll_ReservationExists_returnListWithReservation() {
        // Arrange
        List<Reservation> reservationList = Lists.list(TestDataFactory.reservation);
        Mockito.when(reservationService.findAll()).thenReturn(reservationList);

        List<ReservationSimpleViewDto> reservationSimpleViewDtoList = testEntityMapper.mapToListReservations(reservationList);
        Mockito.when(reservationMapper.mapToList(reservationList)).thenReturn(reservationSimpleViewDtoList);

        // Act
        List<ReservationSimpleViewDto> resultReservationList = reservationFacade.findAll();

        // Assert
        assertThat(resultReservationList).isEqualTo(reservationSimpleViewDtoList);
    }

    @Test
    void createReservation_dbEmpty_returnsNewReservation() {
        // Arrange
        Mockito.when(reservationMapper.mapFromCreate(TestDataFactory.reservationCreateDto)).thenReturn(TestDataFactory.reservation);
        Mockito.when(reservationService.create(TestDataFactory.reservation, "123456789", "Arthur Dent")).thenReturn(TestDataFactory.reservation);
        Mockito.when(reservationMapper.mapToDetailedViewDto(TestDataFactory.reservation)).thenReturn(TestDataFactory.reservationDetailedViewDto);

        // Act
        ReservationDetailedViewDto reservationViewDto = reservationFacade.create(TestDataFactory.reservationCreateDto);

        // Assert
        assertThat(reservationViewDto).isEqualTo(TestDataFactory.reservationDetailedViewDto);
    }

    @Test
    void update_reservationExists_returnsUpdatedReservation() {
        // Arrange
        Mockito.when(reservationMapper.mapFromUpdate(TestDataFactory.reservationUpdateDto)).thenReturn(TestDataFactory.reservation);
        Mockito.when(reservationService.update(TestDataFactory.reservation)).thenReturn(TestDataFactory.reservation);
        Mockito.when(reservationMapper.mapToDetailedViewDto(TestDataFactory.reservation)).thenReturn(TestDataFactory.reservationDetailedViewDto);

        // Act
        ReservationDetailedViewDto reservationDEtailedViewDto = reservationFacade.update(1L, TestDataFactory.reservationUpdateDto);

        // Assert
        assertThat(reservationDEtailedViewDto).isEqualTo(TestDataFactory.reservationDetailedViewDto);
    }

    @Test
    void deleteById_reservationExists_deletesReservation() {
        // Act
        reservationFacade.deleteById(1L);

        // Verify
        Mockito.verify(reservationService).deleteById(1L);
    }
}
