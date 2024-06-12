package com.example.tennis_club.data.dao;

import com.example.tennis_club.data.model.SurfaceType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class SurfaceTypeDao {
    @PersistenceContext
    private EntityManager entityManager;

    public SurfaceTypeDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public SurfaceType findById(Long id) {
        return entityManager.find(SurfaceType.class, id);
    }
}
