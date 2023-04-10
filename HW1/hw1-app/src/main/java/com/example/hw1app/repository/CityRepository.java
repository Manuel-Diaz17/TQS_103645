package com.example.hw1app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hw1app.entities.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{
    City findByName(String name);
}
