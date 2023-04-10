package com.example.hw1app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "polluent")
public class Polluent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "city")
    private City city;

    @Column(name = "pollname")
    private String pollname;

    @Column(name = "conc")
    private double conc;

    @Column(name = "aqius")
    private double aqius;

    @Column(name = "aqicn")
    private double aqicn;

    public Polluent(String pollname, double conc, double aqius, double aqicn){
        this.pollname = pollname;
        this.conc = conc;
        this.aqius = aqius;
        this.aqicn = aqicn;
    }

    public String getPollName(){
        return pollname;
    }

    public double getConc() {
        return conc;
    }

    public double getAqius() {
        return aqius;
    }

    public double getAqicn() {
        return aqicn;
    }

    @Override
    public String toString() {
        return "Polluent [pollname=" + pollname + ", conc=" + conc + ", aqius=" + aqius + ", aqicn=" + aqicn + "]";
    }
    
}
