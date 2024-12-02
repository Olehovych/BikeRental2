package org.laba2.bikerental2.controller;

import org.laba2.bikerental2.dto.StationDTO;
import org.laba2.bikerental2.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class StationController {

    @Autowired
    private StationService stationService;

    @PostMapping
    public StationDTO createStation(@RequestBody StationDTO stationDTO) {
        return stationService.createStation(stationDTO);
    }

    @GetMapping
    public List<StationDTO> getAllStations() {
        return stationService.getAllStations();
    }

    @GetMapping("/{id}")
    public StationDTO getStationById(@PathVariable Long id) {
        return stationService.getStationById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteStation(@PathVariable Long id) {
        stationService.deleteStation(id);
    }
}
