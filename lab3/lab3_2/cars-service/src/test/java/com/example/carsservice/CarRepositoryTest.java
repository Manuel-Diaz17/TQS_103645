package com.example.carsservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.example.carsservice.model.Car;
import com.example.carsservice.repository.CarRepository;

@DataJpaTest
public class CarRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository repository;

    @Test
    void whenFindCarByExistingId_thenReturnCar() {

        Car seat = new Car("Seat", "Ibiza");
        seat.setCarId(1L);
        entityManager.persistAndFlush(seat);

        Car found = repository.findByCarId(seat.getCarId());
        assertThat(found.getModel()).isEqualTo(seat.getModel());

    }

    @Test
    void whenInvalidId_thenReturnNull() {
        Car fromDb = repository.findById(-99L).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Test
    void givenSetOfCars_whenFindAll_thenReturnAllCars() {
        Car seat = new Car("Seat", "Ibiza");
        seat.setCarId(1L);
        Car audi = new Car("Audi", "A4");
        audi.setCarId(2L);
        Car mercedes = new Car("Mercedes", "CLA");
        mercedes.setCarId(3L);

        entityManager.persist(seat);
        entityManager.persist(audi);
        entityManager.persist(mercedes);
        entityManager.flush();

        List<Car> allCars = repository.findAll();

        assertThat(allCars).hasSize(3).extracting(Car::getModel).containsOnly(seat.getModel(), audi.getModel(), mercedes.getModel());
    }
}
