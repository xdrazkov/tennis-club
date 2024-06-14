package com.example.tennis_club.service;

import com.example.tennis_club.data.dao.CourtDao;
import com.example.tennis_club.data.model.Court;
import com.example.tennis_club.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class CourtService {
    private final CourtDao courtDao;

    @Autowired
    CourtService(CourtDao courtDao) {
        this.courtDao = courtDao;
    }

    @Transactional(readOnly = true)
    public Court findById(Long id) {
        return courtDao.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Court> findAll() {
        return courtDao.findAll();
    }

    @Transactional
    public Court create(Court court) {
        if (court.getName() == null || court.getSurfaceType() == null) {
            throw new IllegalArgumentException("Name and surface type are required");
        }
        return courtDao.save(court);
    }

    @Transactional
    public void deleteById(Long id) {
        courtDao.deleteById(id);
    }

    @Transactional
    public Court update(Court newCourt) {
        Court existingCourt = courtDao.findById(newCourt.getId());
        if (existingCourt == null) {
            throw new ResourceNotFoundException("Court with id " + newCourt.getId() + " does not exist");
        }
        newCourt.setName(Objects.requireNonNullElse(newCourt.getName(), existingCourt.getName()));
        newCourt.setSurfaceType(Objects.requireNonNullElse(newCourt.getSurfaceType(), existingCourt.getSurfaceType()));

        courtDao.save(newCourt);
        return findById(newCourt.getId());
    }
}
