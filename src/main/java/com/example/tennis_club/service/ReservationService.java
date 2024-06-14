package com.example.tennis_club.service;

import com.example.tennis_club.data.dao.CustomerDao;
import com.example.tennis_club.data.dao.ReservationDao;
import com.example.tennis_club.data.model.Customer;
import com.example.tennis_club.data.model.Reservation;
import com.example.tennis_club.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
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
        if (reservation.getCourt() == null || customerPhone == null || customerName == null) {
            throw new IllegalArgumentException("Valid court is required");
        }
        Customer customer = customerDao.getByPhoneNumber(customerPhone, customerName);
        reservation.setCustomer(customer);
        List<Reservation> courtReservations = reservationDao.findByCourtId(reservation.getCourt().getId());
        courtReservations.forEach(existingReservation -> {
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
        newReservation.setIsDoubles(Objects.requireNonNullElse(newReservation.getIsDoubles(), existingReservation.getIsDoubles()));

        reservationDao.save(newReservation);
        return findById(newReservation.getId());
    }

    public List<Reservation> findByCourtId(Long courtId) {
        List<Reservation> reservations = reservationDao.findByCourtId(courtId);
        reservations.sort(Comparator.comparing(Reservation::getDateFrom));
        return reservations;
    }

    public List<Reservation> findByPhoneNumber(String phoneNumber, boolean showFutureOnly) {
        return reservationDao.findByPhoneNumber(phoneNumber, showFutureOnly);
    }
}
