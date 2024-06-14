package com.example.tennis_club.rest;

import com.example.tennis_club.api.CourtViewDto;
import com.example.tennis_club.facade.CourtFacade;
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
public class CourtRestControllerTest {
    @Mock
    private CourtFacade courtFacade;
    @InjectMocks
    private CourtRestController courtRestController;

    @Test
    void createCourt_dbEmpty_returnsNewCourt() {
        // Arrange
        Mockito.when(courtFacade.create(TestDataFactory.courtCreateDto)).thenReturn(TestDataFactory.courtViewDto);

        // Act
        ResponseEntity<CourtViewDto> response = courtRestController.create(TestDataFactory.courtCreateDto);

        // Assert
        assertThat(response).isEqualTo(ResponseEntity.status(HttpStatus.CREATED).body(TestDataFactory.courtViewDto));
    }

    @Test
    void findAll_courtExists_returnListWithCourt() {
        // Arrange
        Mockito.when(courtFacade.findAll()).thenReturn(TestDataFactory.courtViewDtoList);

        // Act
        ResponseEntity<List<CourtViewDto>> resultList = courtRestController.findAll();

        // Assert
        assertThat(resultList).isEqualTo(ResponseEntity.ok(TestDataFactory.courtViewDtoList));
    }

    @Test
    void findById_courtExists_returnCourtDTO() {
        // Arrange
        Mockito.when(courtFacade.findById(1L)).thenReturn(TestDataFactory.courtViewDto);

        // Act
        ResponseEntity<CourtViewDto> response = courtRestController.findById(1L);

        // Assert
        assertThat(response).isEqualTo(ResponseEntity.ok(TestDataFactory.courtViewDto));
    }

    @Test
    void update_courtExists_returnsUpdatedMedicalRecord() {
        // Arrange
        Mockito.when(courtFacade.update(1L, TestDataFactory.courtCreateDto)).thenReturn(TestDataFactory.courtViewDto);

        // Act
        ResponseEntity<CourtViewDto> response = courtRestController.update(1L, TestDataFactory.courtCreateDto);

        // Assert
        assertThat(response).isEqualTo(ResponseEntity.ok(TestDataFactory.courtViewDto));
    }

    @Test
    void partialUpdate_courtExists_returnsUpdatedCourt() {
        // Arrange
        Mockito.when(courtFacade.update(1L, TestDataFactory.courtCreateDto)).thenReturn(TestDataFactory.courtViewDto);

        // Act
        ResponseEntity<CourtViewDto> response = courtRestController.partialUpdate(1L, TestDataFactory.courtCreateDto);

        // Assert
        assertThat(response).isEqualTo(ResponseEntity.ok(TestDataFactory.courtViewDto));
    }

    @Test
    void delete_courtExists_deletesCourt() {
        // Act
        courtRestController.delete(1L);

        // Verify
        Mockito.verify(courtFacade).deleteById(1L);
    }
}
