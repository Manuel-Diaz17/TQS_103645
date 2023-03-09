package com.example.carsservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carsservice.exception.ResourceNotFoundException;
import com.example.carsservice.model.Car;
import com.example.carsservice.service.CarManagerService;

@RestController
@RequestMapping("/api")
public class CarController {
    @Autowired
    private CarManagerService service;

    @PostMapping("/createCar" )
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        HttpStatus status = HttpStatus.CREATED;
        Car saved = service.save(car);
        return new ResponseEntity<>(saved, status);
    }

    @GetMapping("/cars")
    public List<Car> getAllCars(){
        return service.getAllCars();
    }

    @GetMapping("/carById/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) throws ResourceNotFoundException{
        HttpStatus status = HttpStatus.OK;
        Car finded = service.getCarDetails(id).orElseThrow(() -> new ResourceNotFoundException("CarId " + id + ", NOT FOUND."));
        return new ResponseEntity<>(finded, status);
    }
    
}
