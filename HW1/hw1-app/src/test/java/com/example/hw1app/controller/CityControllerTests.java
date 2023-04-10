package com.example.hw1app.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.hw1app.entities.City;
import com.example.hw1app.service.ServiceImplementation;

@WebMvcTest(CityRestController.class)
class CityControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ServiceImplementation service;

    @BeforeEach
    void setUp(){
    }

    @Test
    void getAllCitiesTest() throws Exception {
        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City("Alamo", "California", "USA"));
        cities.add(new City("Arden-Arcade", "California", "USA"));

        given(service.getCities("USA", "California")).willReturn(cities);

        mvc.perform(get("/api/list/{country}/{state}", "USA", "California").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0]", is("Alamo"))).andExpect(jsonPath("$[1]", is("Arden-Arcade")));
    }

    @Test
    void getAllStatesTest() throws Exception {
        ArrayList<String> states = new ArrayList<>();
        states.add("Asturias");
        states.add("Catalunya");
        states.add("Ceuta");

        given(service.getStates("Spain")).willReturn(states);

        mvc.perform(get("/api/list/{country}", "Spain").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0]", is("Asturias"))).andExpect(jsonPath("$[1]", is("Catalunya"))).andExpect(jsonPath("$[2]", is("Ceuta")));
    }


    @Test
    void getCityDataTest() throws Exception {
        City city = new City("Alamo", "California", "USA");

        given(service.getCityData("USA", "California", "Alamo")).willReturn(city);

        mvc.perform(get("/api/{country}/{state}/{city}", "USA", "California", "Alamo").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Alamo")));

    }

    @Test
    void getCacheDetailsTest() throws Exception {
        HashMap<String, Integer> cache = new HashMap<>();
        cache.put("hits", 5);
        cache.put("misses", 7);
        cache.put("requests", 2);

        given(service.getCacheDetails()).willReturn(cache);

        mvc.perform(get("/api/cache").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.hits", is(5)))
                .andExpect(jsonPath("$.misses", is(7)))
                .andExpect(jsonPath("$.requests", is(2)));

    }
}
