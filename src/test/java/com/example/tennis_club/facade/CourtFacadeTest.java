package com.example.tennis_club.facade;

import com.example.tennis_club.api.CourtViewDto;
import com.example.tennis_club.data.model.Court;
import com.example.tennis_club.mapper.CourtMapper;
import com.example.tennis_club.service.CourtService;
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
public class CourtFacadeTest {
    private final TestEntityMapper testEntityMapper = Mappers.getMapper(TestEntityMapper.class);

    @Mock
    private CourtService courtService;

    @Mock
    private CourtMapper courtMapper;

    @InjectMocks
    private CourtFacade courtFacade;

    @Test
    void findById_CourtExists_returnCourtDTO() {
        // Arrange
        Mockito.when(courtService.findById(1L)).thenReturn(TestDataFactory.court);
        Mockito.when(courtMapper.mapToDto(TestDataFactory.court)).thenReturn(TestDataFactory.courtViewDto);

        // Act
        CourtViewDto resultMedicalRecord = courtFacade.findById(1L);

        // Assert
        assertThat(resultMedicalRecord).isEqualTo(TestDataFactory.courtViewDto);
    }

    @Test
    void findAll_CourtExists_returnListWithCourt() {
        // Arrange
        List<Court> courtList = Lists.list(TestDataFactory.court);
        Mockito.when(courtService.findAll()).thenReturn(courtList);

        List<CourtViewDto> courtViewDtoList = testEntityMapper.mapToList(courtList);
        Mockito.when(courtMapper.mapToList(courtList)).thenReturn(courtViewDtoList);

        // Act
        List<CourtViewDto> resultCourtList = courtFacade.findAll();

        // Assert
        assertThat(resultCourtList).isEqualTo(courtViewDtoList);
    }

    @Test
    void createCourt_dbEmpty_returnsNewCourt() {
        // Arrange
        Mockito.when(courtMapper.mapToCreate(TestDataFactory.courtViewDto)).thenReturn(TestDataFactory.court);
        Mockito.when(courtService.create(TestDataFactory.court)).thenReturn(TestDataFactory.court);
        Mockito.when(courtMapper.mapToDto(TestDataFactory.court)).thenReturn(TestDataFactory.courtViewDto);

        // Act
        CourtViewDto courtViewDto = courtFacade.create(TestDataFactory.courtCreateDto);

        // Assert
        assertThat(courtViewDto).isEqualTo(TestDataFactory.courtViewDto);
    }

    @Test
    void updateCourt_courtExists_returnsUpdatedCourt() {
        // Arrange
        Mockito.when(courtMapper.mapFromCreate(TestDataFactory.courtCreateDto)).thenReturn(TestDataFactory.court);
        Mockito.when(courtService.update(TestDataFactory.court)).thenReturn(TestDataFactory.court);
        Mockito.when(courtMapper.mapToDto(TestDataFactory.court)).thenReturn(TestDataFactory.courtViewDto);

        // Act
        CourtViewDto medicalRecordBasicViewDto = courtFacade.update(1L, TestDataFactory.courtCreateDto);

        // Assert
        assertThat(medicalRecordBasicViewDto).isEqualTo(TestDataFactory.courtViewDto);
    }

    @Test
    void deleteById_courtExists_deletesCourt() {
        // Act
        courtFacade.deleteById(1L);

        // Verify
        Mockito.verify(courtService).deleteById(1L);
    }

}
