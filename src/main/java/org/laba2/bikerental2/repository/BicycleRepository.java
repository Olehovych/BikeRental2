package org.laba2.bikerental2.repository;

import org.laba2.bikerental2.entity.Bicycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BicycleRepository extends JpaRepository<Bicycle, Long> {
    List<Bicycle> findByStationId(Long stationId);
}
