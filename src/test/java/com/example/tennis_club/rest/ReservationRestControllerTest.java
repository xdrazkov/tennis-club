package com.example.tennis_club.rest;

import com.example.tennis_club.api.ReservationDetailedViewDto;
import com.example.tennis_club.api.ReservationSimpleViewDto;
import com.example.tennis_club.facade.ReservationFacade;
import com.example.tennis_club.util.TestDataFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ReservationRestControllerTest {
    @Mock
    private ReservationFacade reservationFacade;
    @InjectMocks
    private ReservationRestController reservationRestController;

    @Test
    void createReservation_dbEmpty_returnsNewReservation() {
        // Arrange
        Mockito.when(reservationFacade.create(TestDataFactory.reservationCreateDto)).thenReturn(TestDataFactory.reservationDetailedViewDto);

        // Act
        ResponseEntity<ReservationDetailedViewDto> response = reservationRestController.create(TestDataFactory.reservationCreateDto);

        // Assert
        assertThat(response).isEqualTo(ResponseEntity.status(HttpStatus.CREATED).body(TestDataFactory.reservationDetailedViewDto));
    }

    @Test
    void findAll_reservationExists_returnListWithReservation() {
        // Arrange
        Mockito.when(reservationFacade.findAll()).thenReturn(TestDataFactory.reservationSimpleViewDtoList);

        // Act
        ResponseEntity<List<ReservationSimpleViewDto>> resultList = reservationRestController.findAll();

        // Assert
        assertThat(resultList).isEqualTo(ResponseEntity.ok(TestDataFactory.reservationSimpleViewDtoList));
    }

    @Test
    void update_reservationExists_returnsUpdatedMedicalRecord() {
        // Arrange
        Mockito.when(reservationFacade.update(1L, TestDataFactory.reservationUpdateDto)).thenReturn(TestDataFactory.reservationDetailedViewDto);

        // Act
        ResponseEntity<ReservationDetailedViewDto> response = reservationRestController.update(1L, TestDataFactory.reservationUpdateDto);

        // Assert
        assertThat(response).isEqualTo(ResponseEntity.ok(TestDataFactory.reservationDetailedViewDto));
    }

    @Test
    void partialUpdate_reservationExists_returnsUpdatedReservation() {
        // Arrange
        Mockito.when(reservationFacade.update(1L, TestDataFactory.reservationUpdateDto)).thenReturn(TestDataFactory.reservationDetailedViewDto);

        // Act
        ResponseEntity<ReservationDetailedViewDto> response = reservationRestController.partialUpdate(1L, TestDataFactory.reservationUpdateDto);

        // Assert
        assertThat(response).isEqualTo(ResponseEntity.ok(TestDataFactory.reservationDetailedViewDto));
    }

    @Test
    void delete_reservationExists_deletesReservation() {
        // Act
        reservationRestController.delete(1L);

        // Verify
        Mockito.verify(reservationFacade).deleteById(1L);
    }

    @Test
    void findByCourtId_reservationExists_returnReservationDTO() {
        // Arrange
        Mockito.when(reservationFacade.findByCourtId(1L)).thenReturn(TestDataFactory.reservationSimpleViewDtoList);

        // Act
        ResponseEntity<List<ReservationSimpleViewDto>> response = reservationRestController.findByCourtId(1L);

        // Assert
        assertThat(response).isEqualTo(ResponseEntity.ok(TestDataFactory.reservationSimpleViewDtoList));
    }

    @Test
    void findByPhoneNumber_reservationExists_returnReservationDTO() {
        // Arrange
        Mockito.when(reservationFacade.findByPhoneNumber("123456789", false)).thenReturn(TestDataFactory.reservationDetailedViewDtoList);

        // Act
        ResponseEntity<List<ReservationDetailedViewDto>> response = reservationRestController.findByPhoneNumber("123456789", false);

        // Assert
        assertThat(response).isEqualTo(ResponseEntity.ok(TestDataFactory.reservationDetailedViewDtoList));
    }
}
