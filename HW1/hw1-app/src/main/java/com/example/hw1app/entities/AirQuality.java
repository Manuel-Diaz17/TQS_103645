package com.example.hw1app.entities;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "airquality")
public class AirQuality {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "city")
    private City city;

    @Column(name = "aqiUs")
    private Double aqiUs;

    @Column(name = "aqiCn")
    private Double aqiCn;

    @Column(name = "mainPollUs")
    private String mainPolluentUs;

    @Column(name = "mainPollCn")
    private String mainPolluentCn;

    //@Column(name = "polluents")
    //private ArrayList<Polluent> polluents = new ArrayList<Polluent>();
    


    public AirQuality(double aqiUs, double aqiCn, String mainPolluentUs, String mainPolluentCn){
        this.aqiUs = aqiUs;
        this.aqiCn = aqiCn;
        this.mainPolluentUs = mainPolluentUs;
        this.mainPolluentCn = mainPolluentCn;
        //this.polluents = polluents;
    }


    public Double getAqiUs() {
        return aqiUs;
    }

    public Double getAqiCn() {
        return aqiCn;
    }

    public String getMainPolluentUs() {
        return mainPolluentUs;
    }

    public String getMainPolluentCn() {
        return mainPolluentCn;
    }

    //public ArrayList<Polluent> getPolluents() {
    //    return polluents;
    //}

}