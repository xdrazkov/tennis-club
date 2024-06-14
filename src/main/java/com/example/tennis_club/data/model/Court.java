package com.example.tennis_club.data.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "court")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Court {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "court_id")
    private Long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "surface_type_id")
    private SurfaceType surfaceType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "court", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE})
    private List<Reservation> reservations;

    @Column
    private boolean deleted = false;

    public String toString() {
        return "Court(id=" + this.getId() + ", name=" + this.getName() + ", surfaceType=" + this.getSurfaceType() + ", reservations=" + this.getReservations() + ")";
    }
}
