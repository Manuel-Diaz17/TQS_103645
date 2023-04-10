package com.example.hw1app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "timestamp")
    private String timestamp;

    @OneToOne(mappedBy = "city")
    private Weather weather;

    @OneToOne(mappedBy = "city")
    private AirQuality airQuality;

    public City(String name, String state, String country){
        this.name = name;
        this.state = state;
        this.country = country;
    }

    public City(String name, String state, String country, String timestamp, Weather weather, AirQuality airQuality) {
        this.name = name;
        this.state = state;
        this.country = country;
        this.timestamp = timestamp;
        this.weather = weather;
        this.airQuality = airQuality;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Weather getWeather() {
        return weather;
    }

    public AirQuality getAirQuality() {
        return airQuality;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
