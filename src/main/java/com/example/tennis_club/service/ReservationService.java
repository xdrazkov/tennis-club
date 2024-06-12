package com.example.tennis_club.service;

import com.example.tennis_club.data.dao.ReservationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    private final ReservationDao reservationDao;

    @Autowired
    ReservationService(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }
}
