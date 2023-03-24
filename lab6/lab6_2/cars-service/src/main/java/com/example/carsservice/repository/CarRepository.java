package com.example.carsservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.carsservice.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car,Long>{
    public Car findByCarId(Long id);
    public List<Car> findAll();
}
