package com.example.tennis_club.dao;

import com.example.tennis_club.data.dao.CourtDao;
import com.example.tennis_club.data.model.Court;
import com.example.tennis_club.util.TestDataFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourtDaoTest {
    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private CourtDao courtDao;


    @Test
    void findAll_returnsAllCourts() {
        // Arrange
        TypedQuery<Court> query = Mockito.mock(TypedQuery.class);
        List<Court> expectedCourts = Arrays.asList(TestDataFactory.court, TestDataFactory.court2);

        when(entityManager.createQuery("SELECT r FROM Court r", Court.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedCourts);

        // Act
        List<Court> result = courtDao.findAll();

        // Assert
        assertThat(result).isEqualTo(expectedCourts);
        verify(entityManager).createQuery("SELECT r FROM Court r", Court.class);
        verify(query).getResultList();
    }

    @Test
    void findById_returnsCourt() {
        // Arrange
        Long id = 1L;
        Court expectedCourt = TestDataFactory.court;
        when(entityManager.find(Court.class, id)).thenReturn(expectedCourt);

        // Act
        Court result = courtDao.findById(id);

        // Assert
        assertThat(result).isEqualTo(expectedCourt);
        verify(entityManager).find(Court.class, id);
    }

    @Test
    void save_persistsNewCourt() {
        // Arrange
        Court court = TestDataFactory.court;
        court.setId(null);

        // Act
        Court result = courtDao.save(court);

        // Assert
        verify(entityManager).persist(court);
        assertThat(result).isEqualTo(court);
    }

    @Test
    void save_mergesExistingCourt() {
        // Arrange
        Court court = new Court();
        court.setId(1L);
        Court mergedCourt = new Court();
        when(entityManager.merge(court)).thenReturn(mergedCourt);

        // Act
        Court result = courtDao.save(court);

        // Assert
        verify(entityManager).merge(court);
        assertThat(result).isEqualTo(mergedCourt);
    }

    @Test
    void deleteById_removesCourt() {
        // Arrange
        Long id = 1L;
        Court court = TestDataFactory.court;
        when(entityManager.find(Court.class, id)).thenReturn(court);

        // Act
        courtDao.deleteById(id);

        // Assert
        verify(entityManager).remove(court);
    }

    @Test
    void deleteById_doesNothingWhenCourtNotFound() {
        // Arrange
        Long id = 1L;
        when(entityManager.find(Court.class, id)).thenReturn(null);

        // Act
        courtDao.deleteById(id);

        // Assert
        verify(entityManager, never()).remove(any(Court.class));
    }

}
