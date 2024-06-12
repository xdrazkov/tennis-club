package com.example.tennis_club.data.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "surface_type")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SurfaceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "surface_type_id")
    private Long id;

    @Column
    private String name;

    @Column(name = "price_per_minute")
    private int pricePerMinute;

    // TODO
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "surfaceType", cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE})
//    private List<Court> courts;
}
