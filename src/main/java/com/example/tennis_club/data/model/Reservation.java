package com.example.tennis_club.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "reservation")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "court_id")
    private Court court;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column
    private LocalDateTime dateFrom;

    @Column
    private LocalDateTime dateTo;

    @Column(nullable = false)
    private Boolean isDoubles;

    @Column(nullable = false, updatable = false)
    private long cost = 0;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created;

    @PrePersist
    public void onPrePersist() {
        this.cost = calculateCost();
    }

    private int calculateCost() {
        long minutes = dateFrom.until(dateTo, ChronoUnit.MINUTES);
        long cost = minutes * getCourt().getSurfaceType().getPricePerMinute();
        if (isDoubles) {
            cost = (long) (1.5 * cost);
        }
        return (int) cost;
    }
}
