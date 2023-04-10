package com.example.hw1app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hw1app.entities.AirQuality;

@Repository
public interface AirQualityRepository extends JpaRepository<AirQuality, Long>{
    
}
