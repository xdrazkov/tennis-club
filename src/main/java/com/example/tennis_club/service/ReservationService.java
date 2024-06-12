package com.example.tennis_club.service;

import com.example.tennis_club.data.dao.CustomerDao;
import com.example.tennis_club.data.dao.ReservationDao;
import com.example.tennis_club.data.model.Customer;
import com.example.tennis_club.data.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationService {
    private final ReservationDao reservationDao;
    private final CustomerDao customerDao;

    @Autowired
    ReservationService(ReservationDao reservationDao, CustomerDao customerDao) {
        this.reservationDao = reservationDao;
        this.customerDao = customerDao;
    }

    @Transactional
    public Reservation create(Reservation reservation, String customerPhone, String customerName) {
        Customer customer = customerDao.getByPhoneNumber(customerPhone, customerName);
        reservation.setCustomer(customer);
        return reservationDao.save(reservation);
    }
}
