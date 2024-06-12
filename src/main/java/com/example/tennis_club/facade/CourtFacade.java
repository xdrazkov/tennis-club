package com.example.tennis_club.facade;

import com.example.tennis_club.api.CourtCreateDto;
import com.example.tennis_club.api.CourtViewDto;
import com.example.tennis_club.data.model.Court;
import com.example.tennis_club.mapper.CourtMapper;
import com.example.tennis_club.service.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourtFacade {
    private final CourtService courtService;
    private final CourtMapper courtMapper;

    @Autowired
    public CourtFacade(CourtService courtService, CourtMapper courtMapper) {
        this.courtService = courtService;
        this.courtMapper = courtMapper;
    }

    public CourtViewDto createRecord(CourtCreateDto medicalRecord) {
        return courtMapper.mapToDto(courtService.create(courtMapper.mapFromCreate(medicalRecord)));
    }

    public List<CourtViewDto> findAll() {
        return courtMapper.mapToList(courtService.findAll());
    }

    public CourtViewDto findById(Long id) {
        return courtMapper.mapToDto(courtService.findById(id));
    }

    public CourtViewDto updateRecord(Long courtId, CourtCreateDto newRecord) {
        Court newRecordEntity = courtMapper.mapFromCreate(newRecord);
        newRecordEntity.setId(courtId);
        return courtMapper.mapToDto(courtService.update(newRecordEntity));
    }

    public void deleteById(Long id) {
        courtService.deleteById(id);
    }
}
