package com.example.hw1app.service;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.hw1app.entities.City;
import com.example.hw1app.entities.Weather;
import com.example.hw1app.repository.CityRepository;

@ExtendWith(MockitoExtension.class)
public class serviceTests {
    
    @Mock(lenient = true)
    private CityRepository cityRepository;

    @InjectMocks
    private ServiceImplementation serviceImplementation;

    @BeforeEach
    void setUp() throws InterruptedException {
        this.serviceImplementation = new ServiceImplementation();
        City c1 = new City("Aveiro", "Aveiro", "Portugal");
        City c2 = new City("Agueda", "Aveiro", "Portugal");
        ArrayList<City> cidades = new ArrayList<>();
        cidades.add(c1);
        cidades.add(c2);

        when(cityRepository.findByName(c1.getName())).thenReturn(c1);
        when(cityRepository.findByName(c2.getName())).thenReturn(c2);
        when(cityRepository.findAll()).thenReturn(cidades);
    }

    @AfterEach
    void tearDown(){
    }

    @Test
    void getCitiesTest() throws IOException, URISyntaxException {
        ArrayList<City> cities = new ArrayList<>();
        cities = serviceImplementation.getCities("USA", "California");

        City city1 = new City("Acalanes Ridge", "California", "USA");
        City city2 = new City("Blythe", "California","USA");

        assertThat(cities.contains(city1));
        assertThat(cities.contains(city2));
    }

    @Test
    void getStatesTest() throws IOException, URISyntaxException {
        ArrayList<String> states = new ArrayList<>();
        states = serviceImplementation.getStates("Portugal");

        assertThat(states.contains("Braga"));
        assertThat(states.contains("Lisbon"));
    }

    @Test
    void getCountriesTest() throws IOException, URISyntaxException {
        ArrayList<String> countries = new ArrayList<>();
        countries = serviceImplementation.getCountries();

        assertThat(countries.contains("Gabon"));
        assertThat(countries.contains("Georgia"));
        assertThat(countries.contains("Poland"));
        assertThat(countries.contains("Spain"));
    }

    @Test
    void getCityDataTest() throws IOException, URISyntaxException {
        City foundCityAPI = serviceImplementation.getCityData("USA", "California", "Los Angeles");

        assertThat(foundCityAPI.getName()).isEqualTo("Los Angeles");
        assertThat(foundCityAPI.getState()).isEqualTo("California");
        assertThat(foundCityAPI.getCountry()).isEqualTo("USA");
        
    }
}
