package com.example.carsservice;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.carsservice.controller.CarController;
import com.example.carsservice.model.Car;
import com.example.carsservice.service.CarManagerService;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@WebMvcTest(CarController.class)
public class CarControllerRestAssuredTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;

    @BeforeEach
    public void setMockMvc(){
        RestAssuredMockMvc.mockMvc(mvc);
    }

    @Test
    void whenPostCar_thenCreateCar( ) throws Exception {
        Car seat = new Car("Seat", "Ibiza");
        
        when( service.save(Mockito.any()) ).thenReturn( seat);

        RestAssuredMockMvc.given().header("Content-Type", "application/json").body(JsonUtils.toJson(seat))
        .post("/api/createCar")
        .then().assertThat().statusCode(201)
        .and().body("maker", equalTo("Seat"))
        .and().body("model", equalTo("Ibiza"));


        verify(service, times(1)).save(Mockito.any());
    }

    @Test
    void givenManyCars_whenGetCars_thenReturnJsonArray() throws Exception {
        Car seat = new Car("Seat", "Ibiza");
        Car audi = new Car("Audi", "A4");
        Car mercedes = new Car("Mercedes", "CLA");

        List<Car> allCars = Arrays.asList(seat, audi, mercedes);

        when( service.getAllCars()).thenReturn(allCars);

        RestAssuredMockMvc.given().when()
        .get("/api/cars")
        .then().assertThat().statusCode(200)
        .and().body("", hasSize(3))
        .and().body("maker[0]", is(seat.getMaker()))
        .and().body("maker[1]", is(audi.getMaker()))
        .and().body("maker[2]", is(mercedes.getMaker()))
        .and().body("model[0]", is(seat.getModel()))
        .and().body("model[1]", is(audi.getModel()))
        .and().body("model[2]", is(mercedes.getModel()));

        verify(service, times(1)).getAllCars();
    }

    @Test
    void givenCar_whenGetCarById_thenReturnJsonArray() throws Exception {
        Car seat = new Car("seat", "Ibiza");
        seat.setCarId(1L);

        when( service.getCarDetails(1L)).thenReturn(Optional.of(seat));

        RestAssuredMockMvc.given().when()
            .get("/api/carById/1")
            .then().assertThat().statusCode(200)
            .and().body("maker", is(seat.getMaker()))
            .and().body("model", is(seat.getModel()));

        verify(service, times(1)).getCarDetails(1L);
    }

    @Test
    void testGetCarById_withIdInvalid() throws Exception {
        mvc.perform(
            get("/api/carById/1"))
            .andExpect(status().isNotFound());

        verify(service, times(1)).getCarDetails(1L);
    }
}
