package com.example.tennis_club.facade;

import com.example.tennis_club.api.CourtCreateDto;
import com.example.tennis_club.api.CourtViewDto;
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
        return courtMapper.mapToDto(courtService.createRecord(courtMapper.mapFromCreate(medicalRecord)));
    }

    public List<CourtViewDto> findAll() {
        return courtMapper.mapToList(courtService.findAll());
    }
}
