package com.example.carsservice;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.carsservice.model.Car;
import com.example.carsservice.repository.CarRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@TestPropertySource( locations = "application-integrationtest.properties")
public class CarTestWithRealdbIT {
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository carRepository;

    @AfterEach
    public void resetDb() {
        carRepository.deleteAll();
    }

    @Test
     void whenValidInput_thenCreateCar() {
        Car seat = new Car("Seat", "Ibiza");
        seat.setCarId(1L);
        restTemplate.postForEntity("/api/cars", seat, Car.class);

        List<Car> found = carRepository.findAll();
        assertThat(found).extracting(Car::getModel).containsOnly("Ibiza");
    }

    @Test
    void givenCar_whenGetCar_thenStatus200()  {
        
        Car seat = new Car("Seat", "Ibiza");
        seat.setCarId(1L);
        Car audi = new Car("Audi", "A4");
        audi.setCarId(2L);

        carRepository.saveAndFlush(seat);
        carRepository.saveAndFlush(audi);


       ResponseEntity<List<Car>> response = restTemplate
               .exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
               });

       assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
       assertThat(response.getBody()).extracting(Car::getModel).containsExactly("Ibiza", "A4");

   }

   @Test
   void getCar_returnsCarDetails() {

        Car seat = new Car("Seat", "Ibiza");
        seat.setCarId(1L);

        carRepository.saveAndFlush(seat);

        ResponseEntity<Car> entity = restTemplate.getForEntity("/api/cars/1", Car.class);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).extracting(Car::getModel).isEqualTo("Ibiza");
   }
}
