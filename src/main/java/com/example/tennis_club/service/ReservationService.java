package com.example.tennis_club.service;

import com.example.tennis_club.data.dao.CustomerDao;
import com.example.tennis_club.data.dao.ReservationDao;
import com.example.tennis_club.data.model.Customer;
import com.example.tennis_club.data.model.Reservation;
import com.example.tennis_club.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ReservationService {
    private final ReservationDao reservationDao;
    private final CustomerDao customerDao;

    @Autowired
    ReservationService(ReservationDao reservationDao, CustomerDao customerDao) {
        this.reservationDao = reservationDao;
        this.customerDao = customerDao;
    }

    @Transactional(readOnly = true)
    public Reservation findById(Long id) {
        return reservationDao.findById(id);
    }

    @Transactional
    public Reservation create(Reservation reservation, String customerPhone, String customerName) {
        Customer customer = customerDao.getByPhoneNumber(customerPhone, customerName);
        reservation.setCustomer(customer);
        findByCourtId(reservation.getCourt().getId()).forEach(existingReservation -> {
            if (reservation.getDateFrom().isBefore(existingReservation.getDateTo()) &&
                    reservation.getDateTo().isAfter(existingReservation.getDateFrom())) {
                throw new IllegalArgumentException("Court is already reserved for this time");
            }
        });
        return reservationDao.save(reservation);
    }

    @Transactional(readOnly = true)
    public List<Reservation> findAll() {
        return reservationDao.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        reservationDao.deleteById(id);
    }

    @Transactional
    public Reservation update(Reservation newReservation) {
        Reservation existingReservation = reservationDao.findById(newReservation.getId());
        if (existingReservation == null) {
            throw new ResourceNotFoundException("Reservation with id " + newReservation.getId() + " does not exist");
        }
        newReservation.setCourt(Objects.requireNonNullElse(newReservation.getCourt(), existingReservation.getCourt()));
        newReservation.setCustomer(Objects.requireNonNullElse(newReservation.getCustomer(), existingReservation.getCustomer()));
        newReservation.setDateFrom(Objects.requireNonNullElse(newReservation.getDateFrom(), existingReservation.getDateFrom()));
        newReservation.setDateTo(Objects.requireNonNullElse(newReservation.getDateTo(), existingReservation.getDateTo()));
        // TODO handle boolean values
        newReservation.setDoubles(Objects.requireNonNullElse(newReservation.isDoubles(), existingReservation.isDoubles()));

        reservationDao.save(newReservation);
        return findById(newReservation.getId());
    }

    public List<Reservation> findByCourtId(Long courtId) {
        return reservationDao.findByCourtId(courtId);
    }

    public List<Reservation> findByPhoneNumber(String phoneNumber, boolean showFutureOnly) {
        return reservationDao.findByPhoneNumber(phoneNumber, showFutureOnly);
    }
}
