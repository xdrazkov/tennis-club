package com.example.tennis_club.data.dao;

import com.example.tennis_club.data.model.Court;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class CourtDao implements GeneralDao<Court> {
    @PersistenceContext
    private EntityManager entityManager;

    public CourtDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Court> findAll() {
        TypedQuery<Court> query = entityManager.createQuery("SELECT r FROM Court r WHERE r.deleted = false", Court.class);
        return query.getResultList();
    }

    @Override
    public Court findById(Long id) {
        TypedQuery<Court> query = entityManager.createQuery("SELECT r FROM Court r WHERE r.id = :id AND r.deleted = false", Court.class);
        query.setParameter("id", id);
        return query.getResultStream().findFirst().orElse(null);
    }

    @Override
    public Court save(Court court) {
        if (court.getId() == null) {
            entityManager.persist(court);
        } else {
            court = entityManager.merge(court);
        }
        return court;
    }

    @Override
    public void deleteById(Long id) {
        Court court = findById(id);
        if (court != null) {
            court.setDeleted(true);
            entityManager.merge(court);
        }
    }
}
