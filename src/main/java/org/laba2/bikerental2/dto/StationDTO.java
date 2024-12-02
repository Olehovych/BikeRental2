package org.laba2.bikerental2.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class StationDTO {
    private Long id;
    private String name;
    private String location;
    private List<Long> bicycleIds;

}
