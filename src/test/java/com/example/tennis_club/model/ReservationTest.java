package com.example.tennis_club.model;

import com.example.tennis_club.data.model.Court;
import com.example.tennis_club.data.model.Reservation;
import com.example.tennis_club.data.model.SurfaceType;
import com.example.tennis_club.util.TestDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationTest {
    @Mock
    private Court court;


    @Test
    void testCalculateCost() {
        // Arrange
        Reservation testReservation = TestDataFactory.reservation;
        LocalDateTime dateFrom = LocalDateTime.of(2024, 6, 14, 10, 0);
        LocalDateTime dateTo = LocalDateTime.of(2024, 6, 14, 12, 0);
        testReservation.setDateFrom(dateFrom);
        testReservation.setDateTo(dateTo);
        testReservation.setIsDoubles(false);
        testReservation.getCourt().getSurfaceType().setPricePerMinute(12);

        // Act
        testReservation.onPrePersist();

        // Assert
        assertEquals(12 * 120, testReservation.getCost());  // 120 minutes * 2 = 240
    }

    @Test
    void testCalculateCostDoubles() {
        // Arrange
        Reservation testReservation = TestDataFactory.reservation;
        LocalDateTime dateFrom = LocalDateTime.of(2024, 6, 14, 10, 0);
        LocalDateTime dateTo = LocalDateTime.of(2024, 6, 14, 12, 0);
        testReservation.setDateFrom(dateFrom);
        testReservation.setDateTo(dateTo);
        testReservation.setIsDoubles(true);
        testReservation.getCourt().getSurfaceType().setPricePerMinute(12);

        // Act
        testReservation.onPrePersist();

        // Assert
        assertEquals((int) (12 * 120 * 1.5), testReservation.getCost());  // 120 minutes * 2 = 240
    }

}
