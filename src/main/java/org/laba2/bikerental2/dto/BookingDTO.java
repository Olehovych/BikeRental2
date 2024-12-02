package org.laba2.bikerental2.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class BookingDTO {
    private Long id;
    private Long userId;
    private Long bicycleId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    // Getters and Setters
}
