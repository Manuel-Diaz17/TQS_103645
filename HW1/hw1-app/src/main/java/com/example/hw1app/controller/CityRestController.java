package com.example.hw1app.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hw1app.entities.City;
import com.example.hw1app.service.ServiceImplementation;

@RestController
@RequestMapping("/api")
public class CityRestController {
    
    @Autowired
    public ServiceImplementation serviceImplementation;

    @GetMapping(value = "/list/{country}/{state}", produces="application/json")
    public List<String> getAllCities(@PathVariable(value = "country") String country,
                                   @PathVariable(value = "state") String state) throws IOException, URISyntaxException {

        ArrayList<City> cities = this.serviceImplementation.getCities(country, state);

        if(cities == null){
            return null;
        }

        ArrayList<String> cityNames = new ArrayList<>();
        for(City city: cities){
            cityNames.add(city.getName());
        }
        return cityNames;
    }

    @GetMapping(value = "/list/{country}", produces="application/json")
    public List<String> getAllStates(@PathVariable(value = "country") String country) throws IOException, URISyntaxException {

        return this.serviceImplementation.getStates(country);
    }

    @GetMapping(value = "/list", produces="application/json")
    public List<String> getAllCountries() throws IOException, URISyntaxException {

        return this.serviceImplementation.getCountries();
    }

    @GetMapping(value = "/{country}/{state}/{city}", produces = "application/json")
    public City getCityData(@PathVariable(value = "country") String country,
                            @PathVariable(value = "state") String state,
                            @PathVariable(value = "city") String city) throws IOException, URISyntaxException {

        return this.serviceImplementation.getCityData(country, state, city);
    }

    @GetMapping(value = "/cache", produces = "application/json")
    public Map<String, Integer> getCacheDetails(){
        
        return this.serviceImplementation.getCacheDetails();
    }
}
