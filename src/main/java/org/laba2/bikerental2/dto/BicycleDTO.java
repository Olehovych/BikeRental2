package org.laba2.bikerental2.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class BicycleDTO {
    private Long id;
    private String model;
    private String status;
    private Long stationId;
    // Getters and Setters
}
