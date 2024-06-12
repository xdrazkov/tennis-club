package com.example.tennis_club.service;

import com.example.tennis_club.data.dao.ReservationDao;
import com.example.tennis_club.data.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationService {
    private final ReservationDao reservationDao;

    @Autowired
    ReservationService(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    @Transactional
    public Reservation create(Reservation reservation) {
        return reservationDao.save(reservation);
    }
}
