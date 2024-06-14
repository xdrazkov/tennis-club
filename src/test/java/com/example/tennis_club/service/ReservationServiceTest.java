package com.example.tennis_club.service;

import com.example.tennis_club.TennisClubApplication;
import com.example.tennis_club.data.dao.CustomerDao;
import com.example.tennis_club.data.dao.ReservationDao;
import com.example.tennis_club.data.model.Reservation;
import com.example.tennis_club.exceptions.ResourceNotFoundException;
import com.example.tennis_club.util.TestDataFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = TennisClubApplication.class)
public class ReservationServiceTest {
    @Mock
    private ReservationDao reservationDao;

    @Mock
    private CustomerDao customerDao;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    void findById_reservationExists_returnsReservation() {
        // Arrange
        Mockito.when(reservationDao.findById(1L)).thenReturn(TestDataFactory.reservation);

        // Act
        Reservation reservation = reservationService.findById(1L);

        // Assert
        assertThat(reservation).isEqualTo(TestDataFactory.reservation);
    }

    @Test
    void findAll_dbNotEmpty_listsAllReservations() {
        // Arrange
        List<Reservation> reservations = List.of(TestDataFactory.reservation);
        Mockito.when(reservationDao.findAll()).thenReturn(reservations);

        // Act
        List<Reservation> result = reservationService.findAll();

        // Assert
        assertThat(result).isEqualTo(reservations);
    }

    @Test
    void findAll_noReservations_returnsEmptyList() {
        // Arrange
        Mockito.when(reservationDao.findAll()).thenReturn(List.of());

        // Act
        List<Reservation> reservations = reservationService.findAll();

        // Assert
        assertThat(reservations).isEmpty();
    }

    @Test
    void createReservation_dbEmpty_returnsNewReservation() {
        // Arrange
        Reservation reservation = TestDataFactory.reservation;
        Mockito.when(reservationDao.save(reservation)).thenReturn(reservation);
        Mockito.when(customerDao.getByPhoneNumber(reservation.getCustomer().getPhoneNumber(), reservation.getCustomer().getName())).thenReturn(reservation.getCustomer());

        // Act
        Reservation result = reservationService.create(reservation, reservation.getCustomer().getPhoneNumber(), reservation.getCustomer().getName());

        // Assert
        assertThat(result).isEqualTo(reservation);
    }

    @Test
    void createReservation_collision_throwsIllegalArgumentException() {
        // Arrange
        Reservation reservation = TestDataFactory.reservation;
        Reservation existingReservation = TestDataFactory.futureReservation;
        reservation.setDateFrom(existingReservation.getDateTo().minusHours(1));
        reservation.setDateTo(existingReservation.getDateTo().plusHours(1));
        Mockito.when(customerDao.getByPhoneNumber(reservation.getCustomer().getPhoneNumber(), reservation.getCustomer().getName())).thenReturn(reservation.getCustomer());
        Mockito.when(reservationDao.findByCourtId(reservation.getCourt().getId())).thenReturn(List.of(existingReservation));

        // Act
        IllegalArgumentException thrown = org.junit.jupiter.api.Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> reservationService.create(reservation, reservation.getCustomer().getPhoneNumber(), reservation.getCustomer().getName())
        );

        // Assert
        assertThat(thrown.getMessage()).isNotEmpty();
    }

    @Test
    void deleteById_reservationExists_deletesReservation() {
        // Arrange

        // Act
        reservationService.deleteById(1L);

        // Assert
        Mockito.verify(reservationDao).deleteById(1L);
    }

    @Test
    void updateReservation_reservationExists_returnsNewUpdatedReservation() {
        // Arrange
        Reservation reservation = TestDataFactory.reservation;
        Reservation updatedReservation = TestDataFactory.reservation;
        updatedReservation.setDateFrom(TestDataFactory.dateFrom2);
        updatedReservation.setDateTo(TestDataFactory.dateTo2);
        updatedReservation.setCustomer(TestDataFactory.customer2);
        Mockito.when(reservationDao.findById(reservation.getId())).thenReturn(reservation);
        Mockito.when(reservationDao.save(updatedReservation)).thenReturn(updatedReservation);

        // Act
        Reservation result = reservationService.update(updatedReservation);

        // Assert
        assertThat(result).isEqualTo(updatedReservation);
    }

    @Test
    void updateReservation_reservationDoesNotExist_throwsResourceNotFoundException() {
        // Arrange
        Reservation reservation = TestDataFactory.reservation;
        Mockito.when(reservationDao.findById(reservation.getId())).thenReturn(null);

        // Act
        ResourceNotFoundException thrown = org.junit.jupiter.api.Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> reservationService.update(reservation)
        );

        // Assert
        assertThat(thrown.getMessage()).isNotEmpty();
    }

    @Test
    void findByCourtId_reservationExists_returnsReservations() {
        // Arrange
        List<Reservation> reservations = List.of(TestDataFactory.reservation);
        Mockito.when(reservationDao.findByCourtId(1L)).thenReturn(reservations);

        // Act
        List<Reservation> result = reservationService.findByCourtId(1L);

        // Assert
        assertThat(result).isEqualTo(reservations);
    }

    @Test
    void findByCourtId_noReservations_returnsEmptyList() {
        // Arrange
        Mockito.when(reservationDao.findByCourtId(1L)).thenReturn(List.of());

        // Act
        List<Reservation> reservations = reservationService.findByCourtId(1L);

        // Assert
        assertThat(reservations).isEmpty();
    }

    @Test
    void findByCourtId_courtDoesNotExist_returnsEmptyList() {
        // Arrange
        Mockito.when(reservationDao.findByCourtId(1L)).thenReturn(List.of());

        // Act
        List<Reservation> reservations = reservationService.findByCourtId(1L);

        // Assert
        assertThat(reservations).isEmpty();
    }

    @Test
    void findByPhoneNumber_reservationExists_returnsReservations() {
        // Arrange
        List<Reservation> reservations = List.of(TestDataFactory.reservation);
        Mockito.when(reservationDao.findByPhoneNumber(TestDataFactory.customer.getPhoneNumber(), false)).thenReturn(reservations);

        // Act
        List<Reservation> result = reservationService.findByPhoneNumber(TestDataFactory.customer.getPhoneNumber(), false);

        // Assert
        assertThat(result).isEqualTo(reservations);
    }

    @Test
    void findByPhoneNumber_noReservations_returnsEmptyList() {
        // Arrange
        Mockito.when(reservationDao.findByPhoneNumber(TestDataFactory.customer.getPhoneNumber(), false)).thenReturn(List.of());

        // Act
        List<Reservation> reservations = reservationService.findByPhoneNumber(TestDataFactory.customer.getPhoneNumber(), false);

        // Assert
        assertThat(reservations).isEmpty();
    }

}
