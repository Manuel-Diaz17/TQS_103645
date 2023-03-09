package com.example.carsservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.carsservice.model.Car;
import com.example.carsservice.repository.CarRepository;

@Service
public class CarManagerService {
    @Autowired
    private CarRepository repository;

    public Car save(Car car){
        return repository.save(car);
    }

    public List<Car> getAllCars(){
        return repository.findAll();
    }

    public Optional<Car> getCarDetails(Long id){
        return Optional.of(repository.findByCarId(id));
    }
}
