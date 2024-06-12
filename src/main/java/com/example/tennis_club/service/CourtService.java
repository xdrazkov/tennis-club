package com.example.tennis_club.service;

import com.example.tennis_club.data.dao.CourtDao;
import com.example.tennis_club.data.model.Court;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Court createRecord(Court court) {
        return courtDao.save(court);
    }

    @Transactional
    public void deleteById(Long id) {
        courtDao.deleteById(id);
    }
}
