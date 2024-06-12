package com.example.tennis_club.data.dao;

import com.example.tennis_club.data.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public class CustomerDao {
    @PersistenceContext
    private EntityManager entityManager;

    public CustomerDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Customer> findByPhoneNumber(String phoneNumber) {
        TypedQuery<Customer> query = entityManager.createQuery("SELECT c FROM Customer c WHERE c.phoneNumber = :phoneNumber", Customer.class);
        query.setParameter("phoneNumber", phoneNumber);
        return query.getResultList().stream().findFirst();
    }

    // TODO
    // Gets the customer & creates a new one if it doesn't exist
    public Customer getByPhoneNumber(String phoneNumber, String name) {
        Optional<Customer> customer = findByPhoneNumber(phoneNumber);
        if (customer.isPresent()) {
            Customer existingCustomer = customer.get();
            existingCustomer.setName(name);
            existingCustomer = entityManager.merge(existingCustomer);
            return existingCustomer;
        }
        Customer newCcustomer = new Customer();
        newCcustomer.setPhoneNumber(phoneNumber);
        entityManager.persist(newCcustomer);
        return newCcustomer;
    }
}
