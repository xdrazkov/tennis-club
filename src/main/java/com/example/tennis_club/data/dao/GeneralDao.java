package com.example.tennis_club.data.dao;

import java.util.List;

public interface GeneralDao<T> {
    List<T> findAll();
    T findById(Long id);
    T save(T object);
    void deleteById(Long id);
}
