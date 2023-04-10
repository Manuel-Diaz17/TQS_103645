package com.example.hw1app.cache;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.hw1app.entities.City;

import static org.junit.jupiter.api.Assertions.*;

public class CacheTests {
    private AirQualityCache airQualityCache;

    @BeforeEach
    void setUp() throws InterruptedException {
        this.airQualityCache = new AirQualityCache(1);
    }

    @AfterEach
    void tearDown(){
        this.airQualityCache.clearCache();
    }

    @Test
    void addValueTest(){
        assertEquals(0, this.airQualityCache.getCacheSize());
        this.airQualityCache.addValue("Los Angeles", new City("Los Angeles", "California", "USA"));
        assertEquals(1, this.airQualityCache.getCacheSize());
        assertEquals(true, this.airQualityCache.containsCity("Los Angeles"));
    }

    @Test
    void deleteValueTest(){
        assertEquals(0, this.airQualityCache.getCacheSize());
        this.airQualityCache.addValue("Los Angeles", new City("Los Angeles", "California", "USA"));
        this.airQualityCache.addValue("Aveiro", new City("Aveiro", "Aveiro", "Portugal"));
        this.airQualityCache.deleteValue("Los Angeles");
        assertEquals(1, this.airQualityCache.getCacheSize());
        assertEquals(false, this.airQualityCache.containsCity("Los Angeles"));
    }

    @Test
    void cleanByTimeTest() throws InterruptedException {
        this.airQualityCache.addValue("Los Angeles", new City("Los Angeles", "California", "USA"));
        Thread.sleep(2000);
        assertEquals(0, this.airQualityCache.getCacheSize());
    }

    @Test
    void getHitsAndMissesAndRequestsTest(){
        this.airQualityCache.addValue("Los Angeles", new City("Los Angeles", "California", "USA"));
        this.airQualityCache.addValue("Agueda", new City("Agueda", "Aveiro", "Portugal"));
        this.airQualityCache.getCityFromCache("Los Angeles");
        this.airQualityCache.getCityFromCache("Agueda");
        this.airQualityCache.getCityFromCache("Aveiro");
        assertEquals(2, this.airQualityCache.getHits());
        assertEquals(1, this.airQualityCache.getMisses());
        assertEquals(3, this.airQualityCache.getRequests());
    }

}
