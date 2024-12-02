package org.laba2.bikerental2.service;

import org.laba2.bikerental2.dto.StationDTO;
import org.laba2.bikerental2.entity.Station;
import org.laba2.bikerental2.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.laba2.bikerental2.entity.Bicycle;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationService {

    @Autowired
    private StationRepository stationRepository;

    public StationDTO createStation(StationDTO stationDTO) {
        Station station = new Station();
        station.setName(stationDTO.getName());
        station.setLocation(stationDTO.getLocation());
        station = stationRepository.save(station);
        stationDTO.setId(station.getId());
        return stationDTO;
    }

    public List<StationDTO> getAllStations() {
        return stationRepository.findAll().stream()
                .map(station -> {
                    StationDTO dto = new StationDTO();
                    dto.setId(station.getId());
                    dto.setName(station.getName());
                    dto.setLocation(station.getLocation());
                    dto.setBicycleIds(station.getBicycles().stream()
                            .map(Bicycle::getId)
                            .collect(Collectors.toList()));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public StationDTO getStationById(Long id) {
        Station station = stationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Station not found"));
        StationDTO dto = new StationDTO();
        dto.setId(station.getId());
        dto.setName(station.getName());
        dto.setLocation(station.getLocation());
        dto.setBicycleIds(station.getBicycles().stream()
                .map(Bicycle::getId)
                .collect(Collectors.toList()));
        return dto;
    }

    public void deleteStation(Long id) {
        if (!stationRepository.existsById(id)) {
            throw new RuntimeException("Station not found");
        }
        stationRepository.deleteById(id);
    }
}
