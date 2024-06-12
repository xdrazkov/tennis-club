package com.example.tennis_club.data.dao;

import com.example.tennis_club.data.model.Reservation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
        return List.of();
    }

    @Override
    public Reservation findById(Long id) {
        return null;
    }

    @Override
    public Reservation save(Reservation object) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
