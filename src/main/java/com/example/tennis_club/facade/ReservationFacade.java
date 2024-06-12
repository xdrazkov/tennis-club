package com.example.tennis_club.facade;

import com.example.tennis_club.api.ReservationCreateDto;
import com.example.tennis_club.api.ReservationSimpleViewDto;
import com.example.tennis_club.mapper.ReservationMapper;
import com.example.tennis_club.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationFacade {
    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    @Autowired
    public ReservationFacade(ReservationService reservationService, ReservationMapper reservationMapper) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }

    public ReservationSimpleViewDto create(ReservationCreateDto reservation) {
        return reservationMapper.mapToSimpleViewDto(reservationService.create(reservationMapper.mapFromCreate(reservation), reservation.getCustomerPhone(), reservation.getCustomerName()));
    }

    public List<ReservationSimpleViewDto> findAll() {
        return reservationMapper.mapToList(reservationService.findAll());
    }

    public void deleteById(Long id) {
        reservationService.deleteById(id);
    }
}
