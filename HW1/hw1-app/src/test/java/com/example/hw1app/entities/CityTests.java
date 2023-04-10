package com.example.hw1app.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class CityTests {
    
    @Test
    void gettersTest(){
        Weather weather = new Weather(21.0, 95.6, 15.0, 5.4);

        //ArrayList<Polluent> polluents = new ArrayList<>();
        //Polluent p10 = new Polluent("p1", 5.0, 20.0, 20.0);
        //polluents.add(p10);
        //Polluent co = new Polluent("co", 3.0, 34.0, 57.0);
        //polluents.add(co);

        AirQuality airQuality = new AirQuality(10.0, 9.2, "p1", "p1");

        City city = new City("London", "London", "United Kingdom", "timestamp", weather, airQuality );

        assertEquals("London", city.getName());
        assertEquals("London", city.getState());
        assertEquals("United Kingdom", city.getCountry());
        assertEquals("timestamp", city.getTimestamp());
        assertEquals(weather, city.getWeather());
        assertEquals(airQuality, city.getAirQuality());

        assertEquals(21.0, weather.getTemperature());
        assertEquals(95.6, weather.getPressure());
        assertEquals(15.0, weather.getHumidity());
        assertEquals(5.4, weather.getWindSpeed());

        assertEquals(10.0, airQuality.getAqiUs());
        assertEquals(9.2, airQuality.getAqiCn());
        assertEquals("p1", airQuality.getMainPolluentUs());
        assertEquals("p1", airQuality.getMainPolluentCn());

        //assertEquals("p1", p10.getPollName());
        //assertEquals(5.0, p10.getConc());
        //assertEquals(20.0, p10.getAqius());
        //assertEquals(20.0, p10.getAqicn());

        //assertEquals("co", co.getPollName());
        //assertEquals(3.0, co.getConc());
        //assertEquals(34.0, co.getAqius());
        //assertEquals(57.0, co.getAqicn());
    }
}
