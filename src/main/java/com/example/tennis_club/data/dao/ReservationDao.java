package com.example.tennis_club.data.dao;

import com.example.tennis_club.data.model.Reservation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ReservationDao implements GeneralDao<Reservation> {
    @PersistenceContext
    private EntityManager entityManager;

    public ReservationDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Reservation> findAll() {
        TypedQuery<Reservation> query = entityManager.createQuery("SELECT r FROM Reservation r", Reservation.class);
        return query.getResultList();
    }

    @Override
    public Reservation findById(Long id) {
        return entityManager.find(Reservation.class, id);
    }

    @Override
    public Reservation save(Reservation reservation) {
        if (reservation.getId() == null) {
            entityManager.persist(reservation);
        } else {
            reservation = entityManager.merge(reservation);
        }
        return reservation;
    }

    @Override
    public void deleteById(Long id) {
        Reservation reservation = findById(id);
        if (reservation != null) {
            entityManager.remove(reservation);
        }
    }
}
