package com.example.tennis_club.service;

import com.example.tennis_club.TennisClubApplication;
import com.example.tennis_club.data.dao.CourtDao;
import com.example.tennis_club.data.model.Court;
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
public class CourtServiceTest {
    @Mock
    private CourtDao courtDao;

    @InjectMocks
    private CourtService courtService;

    @Test
    void findById_courtExists_returnsCourt() {
        // Arrange
        Mockito.when(courtDao.findById(1L)).thenReturn(TestDataFactory.court);

        // Act
        Court court = courtService.findById(1L);

        // Assert
        assertThat(court).isEqualTo(TestDataFactory.court);
    }

    @Test
    void findAll_dbNotEmpty_listsAllCourts() {
        // Arrange
        List<Court> courts = List.of(TestDataFactory.court);
        Mockito.when(courtDao.findAll()).thenReturn(courts);

        // Act
        List<Court> result = courtService.findAll();

        // Assert
        assertThat(result).isEqualTo(courts);
    }

    @Test
    void findAll_noCourts_returnsEmptyList() {
        // Arrange
        Mockito.when(courtDao.findAll()).thenReturn(List.of());

        // Act
        List<Court> courts = courtService.findAll();

        // Assert
        assertThat(courts).isEmpty();
    }

    @Test
    void createCourt_dbEmpty_returnsNewCourt() {
        // Arrange
        Court court = TestDataFactory.court;
        Mockito.when(courtDao.save(court)).thenReturn(court);

        // Act
        Court result = courtService.create(court);

        // Assert
        assertThat(result).isEqualTo(court);
    }

    @Test
    void deleteById_courtExists_deletesCourt() {
        // Arrange

        // Act
        courtService.deleteById(1L);

        // Assert
        Mockito.verify(courtDao).deleteById(1L);
    }

    @Test
    void updateCourt_courtExists_returnsNewUpdatedCourt() {
        // Arrange
        Court court = TestDataFactory.court;
        Court updatedCourt = TestDataFactory.court;
        updatedCourt.setName("New name");
        updatedCourt.setSurfaceType(TestDataFactory.surfaceType2);
        Mockito.when(courtDao.findById(court.getId())).thenReturn(court);
        Mockito.when(courtDao.save(updatedCourt)).thenReturn(updatedCourt);

        // Act
        Court result = courtService.update(updatedCourt);

        // Assert
        assertThat(result).isEqualTo(updatedCourt);
    }

    @Test
    void updateCourt_courtDoesNotExist_throwsResourceNotFoundException() {
        // Arrange
        Court court = TestDataFactory.court;
        Mockito.when(courtDao.findById(court.getId())).thenReturn(null);

        // Act
        ResourceNotFoundException thrown = org.junit.jupiter.api.Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> courtService.update(court)
        );

        // Assert
        assertThat(thrown.getMessage()).isNotEmpty();
    }

}
