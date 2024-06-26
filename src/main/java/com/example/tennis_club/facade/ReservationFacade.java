package com.example.tennis_club.facade;

import com.example.tennis_club.api.ReservationCreateDto;
import com.example.tennis_club.api.ReservationDetailedViewDto;
import com.example.tennis_club.api.ReservationSimpleViewDto;
import com.example.tennis_club.api.ReservationUpdateDto;
import com.example.tennis_club.data.model.Reservation;
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

    public ReservationDetailedViewDto create(ReservationCreateDto reservation) {
        return reservationMapper.mapToDetailedViewDto(reservationService.create(reservationMapper.mapFromCreate(reservation), reservation.getCustomerPhone(), reservation.getCustomerName()));
    }

    public List<ReservationSimpleViewDto> findAll() {
        return reservationMapper.mapToList(reservationService.findAll());
    }

    public ReservationDetailedViewDto update(Long reservationId, ReservationUpdateDto newReservation) {
        Reservation newReservationEntity = reservationMapper.mapFromUpdate(newReservation);
        newReservationEntity.setId(reservationId);
        return reservationMapper.mapToDetailedViewDto(reservationService.update(newReservationEntity));
    }
    
    public void deleteById(Long id) {
        reservationService.deleteById(id);
    }

    public List<ReservationSimpleViewDto> findByCourtId(Long courtId) {
        return reservationMapper.mapToList(reservationService.findByCourtId(courtId));
    }

    public List<ReservationDetailedViewDto> findByPhoneNumber(String phoneNumber, boolean showFutureOnly) {
        return reservationMapper.mapToDetailedViewList(reservationService.findByPhoneNumber(phoneNumber, showFutureOnly));
    }
}
