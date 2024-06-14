package com.example.tennis_club.dao;

import com.example.tennis_club.data.dao.SurfaceTypeDao;
import com.example.tennis_club.data.model.SurfaceType;
import com.example.tennis_club.util.TestDataFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class SurfaceTypeDaoTest {
    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private SurfaceTypeDao surfaceTypeDao;

    @Test
    void findById_surfaceTypeExists_returnsSurfaceType() {
        // Arrange
        Mockito.when(entityManager.find(SurfaceType.class, 1L)).thenReturn(TestDataFactory.surfaceType);

        // Act
        SurfaceType foundSurfaceType = surfaceTypeDao.findById(1L);

        // Assert
        assertThat(foundSurfaceType).isEqualTo(TestDataFactory.surfaceType);
    }
}
