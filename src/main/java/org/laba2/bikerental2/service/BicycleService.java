package org.laba2.bikerental2.service;

import org.laba2.bikerental2.dto.BicycleDTO;
import org.laba2.bikerental2.entity.Bicycle;
import org.laba2.bikerental2.entity.BicycleStatus;
import org.laba2.bikerental2.entity.Station;
import org.laba2.bikerental2.repository.BicycleRepository;
import org.laba2.bikerental2.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BicycleService {

    @Autowired
    private BicycleRepository bicycleRepository;

    @Autowired
    private StationRepository stationRepository;

    // Створення нового велосипеда
    public BicycleDTO createBicycle(BicycleDTO bicycleDTO) {
        Bicycle bicycle = new Bicycle();
        bicycle.setModel(bicycleDTO.getModel());
        bicycle.setStatus(BicycleStatus.valueOf(bicycleDTO.getStatus()));

        // Зв'язування зі станцією
        Station station = stationRepository.findById(bicycleDTO.getStationId())
                .orElseThrow(() -> new RuntimeException("Station not found"));
        bicycle.setStation(station);

        bicycle = bicycleRepository.save(bicycle);

        return mapToDTO(bicycle);
    }

    // Отримання всіх велосипедів
    public List<BicycleDTO> getAllBicycles() {
        return bicycleRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Отримання велосипеда за ID
    public BicycleDTO getBicycleById(Long id) {
        Bicycle bicycle = bicycleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bicycle not found"));
        return mapToDTO(bicycle);
    }

    // Оновлення велосипеда
    public BicycleDTO updateBicycle(Long id, BicycleDTO bicycleDTO) {
        Bicycle bicycle = bicycleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bicycle not found"));

        bicycle.setModel(bicycleDTO.getModel());
        bicycle.setStatus(BicycleStatus.valueOf(bicycleDTO.getStatus()));

        // Оновлення станції
        Station station = stationRepository.findById(bicycleDTO.getStationId())
                .orElseThrow(() -> new RuntimeException("Station not found"));
        bicycle.setStation(station);

        bicycle = bicycleRepository.save(bicycle);

        return mapToDTO(bicycle);
    }

    // Видалення велосипеда
    public void deleteBicycle(Long id) {
        if (!bicycleRepository.existsById(id)) {
            throw new RuntimeException("Bicycle not found");
        }
        bicycleRepository.deleteById(id);
    }

    // Мапінг Bicycle -> BicycleDTO
    private BicycleDTO mapToDTO(Bicycle bicycle) {
        BicycleDTO dto = new BicycleDTO();
        dto.setId(bicycle.getId());
        dto.setModel(bicycle.getModel());
        dto.setStatus(bicycle.getStatus().name());
        dto.setStationId(bicycle.getStation() != null ? bicycle.getStation().getId() : null);
        return dto;
    }
}
