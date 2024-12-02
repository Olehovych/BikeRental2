package org.laba2.bikerental2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Bicycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;

    @Enumerated(EnumType.STRING)
    private BicycleStatus status;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;

}
