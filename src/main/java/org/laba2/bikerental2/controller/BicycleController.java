package org.laba2.bikerental2.controller;

import org.laba2.bikerental2.dto.BicycleDTO;
import org.laba2.bikerental2.entity.Bicycle;
import org.laba2.bikerental2.service.BicycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bicycles")
public class BicycleController {

    @Autowired
    private BicycleService bicycleService;

    @PostMapping
    public BicycleDTO createBicycle(@RequestBody BicycleDTO bicycleDTO) {
        return bicycleService.createBicycle(bicycleDTO);
    }

    @GetMapping
    public List<BicycleDTO> getAllBicycles() {
        return bicycleService.getAllBicycles();
    }

    @GetMapping("/{id}")
    public BicycleDTO getBicycleById(@PathVariable Long id) {
        return bicycleService.getBicycleById(id);
    }

    @PutMapping("/{id}")
    public BicycleDTO updateBicycle(@PathVariable Long id, @RequestBody BicycleDTO bicycleDTO) {
        return bicycleService.updateBicycle(id, bicycleDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBicycle(@PathVariable Long id) {
        bicycleService.deleteBicycle(id);
    }
}

