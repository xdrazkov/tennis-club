package com.example.tennis_club.dao;

import com.example.tennis_club.data.dao.CustomerDao;
import com.example.tennis_club.data.model.Customer;
import com.example.tennis_club.util.TestDataFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerDaoTest {
    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private CustomerDao customerDao;

    @Test
    void findByPhoneNumber_returnsCustomer() {
        // Arrange
        String phoneNumber = "1234567890";
        Customer expectedCustomer = TestDataFactory.customer;
        TypedQuery<Customer> query = mock(TypedQuery.class);

        when(entityManager.createQuery("SELECT c FROM Customer c WHERE c.phoneNumber = :phoneNumber", Customer.class)).thenReturn(query);
        when(query.setParameter("phoneNumber", phoneNumber)).thenReturn(query);
        when(query.getResultList()).thenReturn(Arrays.asList(expectedCustomer));

        // Act
        Optional<Customer> result = customerDao.findByPhoneNumber(phoneNumber);

        // Assert
        assertThat(result).isPresent().contains(expectedCustomer);
        verify(entityManager).createQuery("SELECT c FROM Customer c WHERE c.phoneNumber = :phoneNumber", Customer.class);
        verify(query).setParameter("phoneNumber", phoneNumber);
        verify(query).getResultList();
    }

    @Test
    void findByPhoneNumber_returnsEmptyOptionalWhenNoCustomerFound() {
        // Arrange
        String phoneNumber = "1234567890";
        TypedQuery<Customer> query = mock(TypedQuery.class);

        when(entityManager.createQuery("SELECT c FROM Customer c WHERE c.phoneNumber = :phoneNumber", Customer.class)).thenReturn(query);
        when(query.setParameter("phoneNumber", phoneNumber)).thenReturn(query);
        when(query.getResultList()).thenReturn(Arrays.asList());

        // Act
        Optional<Customer> result = customerDao.findByPhoneNumber(phoneNumber);

        // Assert
        assertThat(result).isEmpty();
        verify(entityManager).createQuery("SELECT c FROM Customer c WHERE c.phoneNumber = :phoneNumber", Customer.class);
        verify(query).setParameter("phoneNumber", phoneNumber);
        verify(query).getResultList();
    }

    @Test
    void getByPhoneNumber_updatesExistingCustomer() {
        // Arrange
        String phoneNumber = "1234567890";
        String name = "Arthur Dent";
        Customer existingCustomer = TestDataFactory.customer;
        existingCustomer.setPhoneNumber(phoneNumber);
        TypedQuery<Customer> query = mock(TypedQuery.class);

        when(entityManager.createQuery("SELECT c FROM Customer c WHERE c.phoneNumber = :phoneNumber", Customer.class)).thenReturn(query);
        when(query.setParameter("phoneNumber", phoneNumber)).thenReturn(query);
        when(query.getResultList()).thenReturn(Arrays.asList(existingCustomer));
        when(entityManager.merge(existingCustomer)).thenReturn(existingCustomer);

        // Act
        Customer result = customerDao.getByPhoneNumber(phoneNumber, name);

        // Assert
        assertThat(result).isEqualTo(existingCustomer);
        assertThat(result.getName()).isEqualTo(name);
        verify(entityManager).createQuery("SELECT c FROM Customer c WHERE c.phoneNumber = :phoneNumber", Customer.class);
        verify(query).setParameter("phoneNumber", phoneNumber);
        verify(query).getResultList();
        verify(entityManager).merge(existingCustomer);
    }

    @Test
    void getByPhoneNumber_createsNewCustomerWhenNotFound() {
        // Arrange
        String phoneNumber = "1234567890";
        String name = "Arthur Dent";
        TypedQuery<Customer> query = mock(TypedQuery.class);

        when(entityManager.createQuery("SELECT c FROM Customer c WHERE c.phoneNumber = :phoneNumber", Customer.class)).thenReturn(query);
        when(query.setParameter("phoneNumber", phoneNumber)).thenReturn(query);
        when(query.getResultList()).thenReturn(Arrays.asList());

        // Act
        Customer result = customerDao.getByPhoneNumber(phoneNumber, name);

        // Assert
        assertThat(result.getPhoneNumber()).isEqualTo(phoneNumber);
        verify(entityManager).createQuery("SELECT c FROM Customer c WHERE c.phoneNumber = :phoneNumber", Customer.class);
        verify(query).setParameter("phoneNumber", phoneNumber);
        verify(query).getResultList();
        verify(entityManager).persist(result);
    }
}
