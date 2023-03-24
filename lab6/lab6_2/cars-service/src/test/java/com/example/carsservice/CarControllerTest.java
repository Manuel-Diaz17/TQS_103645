package com.example.carsservice;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

@WebMvcTest(CarController.class)
public class CarControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;

    @Test
    void whenPostCar_thenCreateCar( ) throws Exception {
        Car seat = new Car("Seat", "Ibiza");
        
        when( service.save(Mockito.any()) ).thenReturn( seat);

        mvc.perform(
            post("/api/createCar").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(seat)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.maker", is("Seat")))
            .andExpect(jsonPath("$.model", is("Ibiza")));


        verify(service, times(1)).save(Mockito.any());
    }

    @Test
    void givenManyCars_whenGetCars_thenReturnJsonArray() throws Exception {
        Car seat = new Car("Seat", "Ibiza");
        Car audi = new Car("Audi", "A4");
        Car mercedes = new Car("Mercedes", "CLA");

        List<Car> allCars = Arrays.asList(seat, audi, mercedes);

        when( service.getAllCars()).thenReturn(allCars);

        mvc.perform(
            get("/api/cars").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].maker", is(seat.getMaker())))
            .andExpect(jsonPath("$[1].maker", is(audi.getMaker())))
            .andExpect(jsonPath("$[2].maker", is(mercedes.getMaker())))
            .andExpect(jsonPath("$[0].model", is(seat.getModel())))
            .andExpect(jsonPath("$[1].model", is(audi.getModel())))
            .andExpect(jsonPath("$[2].model", is(mercedes.getModel())));

        verify(service, times(1)).getAllCars();
    }

    @Test
    void givenCar_whenGetCarById_thenReturnJsonArray() throws Exception {
        Car seat = new Car("seat", "Ibiza");
        seat.setCarId(1L);

        when( service.getCarDetails(1L)).thenReturn(Optional.of(seat));
        mvc.perform(
            get("/api/carById/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.maker", is(seat.getMaker())))
            .andExpect(jsonPath("$.model", is(seat.getModel())));

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
