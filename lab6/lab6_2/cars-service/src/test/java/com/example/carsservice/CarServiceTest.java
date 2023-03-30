package com.example.carsservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.carsservice.model.Car;
import com.example.carsservice.repository.CarRepository;
import com.example.carsservice.service.CarManagerService;


@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    @Mock( lenient = true)
    private CarRepository repository;

    @InjectMocks
    private CarManagerService service;

    @BeforeEach
    public void setUp() {

        Car seat = new Car("Seat", "Ibiza");
        seat.setCarId(1L);

        Car audi = new Car("Audi", "A4");
        Car mercedes = new Car("Mercedes", "CLA");

        List<Car> allCars = Arrays.asList(seat, audi, mercedes);

        Mockito.when(repository.findByCarId(seat.getCarId())).thenReturn(seat);
        Mockito.when(repository.findAll()).thenReturn(allCars);
        Mockito.when(repository.findByCarId(-99L)).thenReturn(null);

    }

    @Test
     void whenValidId_thenCarShouldBeFound() {

        Car fromDb = service.getCarDetails(1L).get();
        assertThat(fromDb.getMaker()).isEqualTo("Seat");
        
        verifyFindByIdIsCalledOnce();

    }

    @Test
     void whenInValidId_thenCarShouldNotBeFound() {

        assertThrows(NullPointerException.class, () -> {
            Optional<Car> fromDb = service.getCarDetails(99L);
            assertThat(fromDb).isNull();

        });

        verifyFindByIdIsCalledOnce();

    }

    @Test
     void given3Cars_whengetAll_thenReturn3Records() {

        Car seat = new Car("Seat", "Ibiza");
        Car audi = new Car("Audi", "A4");
        Car mercedes = new Car("Mercedes", "CLA");

        List<Car> allCars = service.getAllCars();
        verifyFindAllCarsIsCalledOnce();
        assertThat(allCars).hasSize(3).extracting(Car::getMaker).contains(seat.getMaker(), audi.getMaker(), mercedes.getMaker());

    }

    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(repository, VerificationModeFactory.times(1)).findByCarId(Mockito.anyLong());
    }

    private void verifyFindAllCarsIsCalledOnce() {
        Mockito.verify(repository, VerificationModeFactory.times(1)).findAll();
    }
}
