package com.example.hw1app.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.hw1app.entities.City;

@DataJpaTest
class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void findCityByNameAndIdTest(){
        City city = new City("Los Angeles", "California", "USA");
        testEntityManager.persistAndFlush(city);

        City cityByName = cityRepository.findByName(city.getName());
        assertThat(cityByName).isEqualTo(city);

        City cityById = cityRepository.findById(city.getId()).orElse(null);
        assertThat(cityById).isNotNull();
        assertThat(cityById.getName()).isEqualTo(city.getName());
    }

    @Test
    void findAllCitiesTest(){
        City city1 = new City("Aveiro", "Aveiro", "Portugal");
        City city2 = new City("Agueda", "Aveiro", "Portugal");
        City city3 = new City("Espinho", "Aveiro", "Portugal");

        testEntityManager.persist(city1);
        testEntityManager.persist(city2);
        testEntityManager.persist(city3);
        testEntityManager.flush();

        List<City> cities = cityRepository.findAll();

        assertThat(cities).hasSize(3).extracting(City::getName).containsOnly(city1.getName(), city2.getName(), city3.getName());
    }
}
