package com.example.tennis_club.data.dao;

import com.example.tennis_club.data.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CustomerDao {
    @PersistenceContext
    private EntityManager entityManager;

    public CustomerDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Customer findByPhoneNumber(String phoneNumber) {
        TypedQuery<Customer> query = entityManager.createQuery("SELECT c FROM Customer c WHERE c.phoneNumber = :phoneNumber", Customer.class);
        query.setParameter("phoneNumber", phoneNumber);
        return query.getSingleResult();
    }

    // Gets the customer & creates a new one if it doesn't exist
    public Customer getByPhoneNumber(String phoneNumber, String name) {
        Customer customer = findByPhoneNumber(phoneNumber);
        if (customer == null) {
            customer = new Customer();
            customer.setPhoneNumber(phoneNumber);
            entityManager.persist(customer);
            return customer;
        }
        customer.setName(name);
        customer = entityManager.merge(customer);
        return customer;
    }
}
