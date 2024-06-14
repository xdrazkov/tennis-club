package com.example.tennis_club.dao;

import com.example.tennis_club.data.dao.ReservationDao;
import com.example.tennis_club.data.model.Court;
import com.example.tennis_club.data.model.Reservation;
import com.example.tennis_club.util.TestDataFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservationDaoTest {
    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private ReservationDao reservationDao;


    @Test
    void findAll_returnsAllReservations() {
        // Arrange
        TypedQuery<Reservation> query = mock(TypedQuery.class);
        List<Reservation> expectedReservations = Arrays.asList(TestDataFactory.reservation,TestDataFactory.futureReservation);
        String expectedQuery = "SELECT r FROM Reservation r WHERE r.deleted = false";

        when(entityManager.createQuery(expectedQuery, Reservation.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedReservations);

        // Act
        List<Reservation> result = reservationDao.findAll();

        // Assert
        assertThat(result).isEqualTo(expectedReservations);
        verify(entityManager).createQuery(expectedQuery, Reservation.class);
        verify(query).getResultList();
    }

    @Test
    void save_persistsNewReservation() {
        // Arrange
        Reservation reservation = TestDataFactory.reservation;
        reservation.setId(null);

        // Act
        Reservation result = reservationDao.save(reservation);

        // Assert
        verify(entityManager).persist(reservation);
        assertThat(result).isEqualTo(reservation);
    }

    @Test
    void save_mergesExistingReservation() {
        // Arrange
        Reservation reservation = TestDataFactory.reservation;
        reservation.setId(1L);
        Reservation mergedReservation = new Reservation();
        when(entityManager.merge(reservation)).thenReturn(mergedReservation);

        // Act
        Reservation result = reservationDao.save(reservation);

        // Assert
        verify(entityManager).merge(reservation);
        assertThat(result).isEqualTo(mergedReservation);
    }


    @Test
    void deleteById_removesReservation() {
        // Arrange
        Long id = 1L;
        Reservation reservation = TestDataFactory.reservation;

        TypedQuery<Reservation> query = Mockito.mock(TypedQuery.class);
        String expectedQuery = "SELECT r FROM Reservation r WHERE r.id = :id AND r.deleted = false";
        when(entityManager.createQuery(expectedQuery, Reservation.class)).thenReturn(query);
        when(query.setParameter("id", id)).thenReturn(query);
        when(query.getResultStream()).thenReturn(Arrays.stream(new Reservation[]{reservation}));

        // Act
        reservationDao.deleteById(id);

        // Assert
        verify(entityManager).merge(reservation);
        assertThat(reservation.isDeleted()).isTrue();
    }

    @Test
    void deleteById_doesNothingWhenReservationNotFound() {
        // Arrange
        Long id = 1L;
        Reservation reservation = TestDataFactory.reservation;

        TypedQuery<Reservation> query = Mockito.mock(TypedQuery.class);
        String expectedQuery = "SELECT r FROM Reservation r WHERE r.id = :id AND r.deleted = false";
        when(entityManager.createQuery(expectedQuery, Reservation.class)).thenReturn(query);
        when(query.setParameter("id", id)).thenReturn(query);
        when(query.getResultStream()).thenReturn(Arrays.stream(new Reservation[]{}));

        // Act
        reservationDao.deleteById(id);

        // Assert
        verify(entityManager, never()).remove(any(Court.class));
    }

    @Test
    void findByCourtId_returnsReservationsForCourt() {
        // Arrange
        Long courtId = 1L;
        TypedQuery<Reservation> query = mock(TypedQuery.class);
        List<Reservation> expectedReservations = Arrays.asList(TestDataFactory.reservation,TestDataFactory.futureReservation);
        String expectedQuery = "SELECT r FROM Reservation r WHERE r.court.id = :courtId AND r.deleted = false";

        when(entityManager.createQuery(expectedQuery, Reservation.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedReservations);
        when(query.setParameter("courtId", courtId)).thenReturn(query);

        // Act
        List<Reservation> result = reservationDao.findByCourtId(courtId);

        // Assert
        assertThat(result).isEqualTo(expectedReservations);
        verify(entityManager).createQuery(expectedQuery, Reservation.class);
        verify(query).setParameter("courtId", courtId);
        verify(query).getResultList();
    }

    @Test
    void findByPhoneNumber_returnsReservationsForPhoneNumber() {
        // Arrange
        String phoneNumber = "1234567890";
        boolean showFutureOnly = false;
        TypedQuery<Reservation> query = mock(TypedQuery.class);
        List<Reservation> expectedReservations = Arrays.asList(TestDataFactory.reservation,TestDataFactory.futureReservation);
        String expectedQuery = "SELECT r FROM Reservation r WHERE r.customer.phoneNumber = :phoneNumber AND r.deleted = false";

        when(entityManager.createQuery(expectedQuery, Reservation.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedReservations);
        when(query.setParameter("phoneNumber", phoneNumber)).thenReturn(query);

        // Act
        List<Reservation> result = reservationDao.findByPhoneNumber(phoneNumber, showFutureOnly);

        // Assert
        assertThat(result).isEqualTo(expectedReservations);
        verify(entityManager).createQuery(expectedQuery, Reservation.class);
        verify(query).setParameter("phoneNumber", phoneNumber);
        verify(query).getResultList();
    }

    @Test
    void findByPhoneNumber_returnsFutureReservationsForPhoneNumber() {
        // Arrange
        String phoneNumber = "1234567890";
        boolean showFutureOnly = true;
        TypedQuery<Reservation> query = mock(TypedQuery.class);
        List<Reservation> expectedReservations = Arrays.asList(TestDataFactory.futureReservation);
        String expectedQuery = "SELECT r FROM Reservation r WHERE r.customer.phoneNumber = :phoneNumber AND r.deleted = false AND (r.dateFrom > :date)";

        when(entityManager.createQuery(expectedQuery, Reservation.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedReservations);
        when(query.setParameter("phoneNumber", phoneNumber)).thenReturn(query);

        // Act
        List<Reservation> result = reservationDao.findByPhoneNumber(phoneNumber, showFutureOnly);

        // Assert
        assertThat(result).isEqualTo(expectedReservations);
        verify(entityManager).createQuery(expectedQuery, Reservation.class);
        verify(query).setParameter("phoneNumber", phoneNumber);
        verify(query).getResultList();
    }
}
