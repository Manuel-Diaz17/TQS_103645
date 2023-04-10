package com.example.hw1app.service;

import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import com.example.hw1app.cache.AirQualityCache;
import com.example.hw1app.entities.AirQuality;
import com.example.hw1app.entities.City;
import com.example.hw1app.entities.Polluent;
import com.example.hw1app.entities.Weather;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

@Service
public class ServiceImplementation {
    
    private static final String PRIVATEKEY =  "0e517d7a-69e1-4941-a750-c726646ee402";
    private static final String URL = "https://api.airvisual.com/v2/";

    private CloseableHttpClient client;

    private AirQualityCache cache = new AirQualityCache(60);

    public ServiceImplementation() throws InterruptedException {
        this.client = HttpClients.createDefault();
    }

    public ArrayList<City> getCities(String country, String state) throws IOException, URISyntaxException {

        String url = URL + "cities?state=" + state + "&country=" + country;
        ArrayList<City> cities = new ArrayList<>();

        URIBuilder builder = new URIBuilder(url.replaceAll(" ", "%20"));
        String response = constructUrlRequest(builder.build().toString());

        JSONObject responseJson = new JSONObject(response);

        if(responseJson.get("status").toString().equals("fail")){
            return null;
        }

        JSONArray data = new JSONArray(responseJson.get("data").toString());

        for (Object obj: data){
            JSONObject jsonObj = (JSONObject) obj;
            City city = new City(jsonObj.get("city").toString(), state, country);
            cities.add(city);
        }
        return cities;
    }

    public ArrayList<String> getStates(String country) throws IOException, URISyntaxException {

        String url = URL + "states?country=" + country;
        ArrayList<String> states = new ArrayList<>();

        URIBuilder builder = new URIBuilder(url.replaceAll(" ", "%20"));
        String response = constructUrlRequest(builder.build().toString());

        JSONObject responseJson = new JSONObject(response);

        if(responseJson.get("status").toString() == "fail"){
            return null;
        }

        JSONArray data = new JSONArray(responseJson.get("data").toString());

        for (Object obj: data){
            JSONObject jsonObj = (JSONObject) obj;
            states.add(jsonObj.get("state").toString());
        }

        return states;
    }

    public ArrayList<String> getCountries() throws IOException, URISyntaxException {

        String url = URL + "countries?";
        ArrayList<String> countries = new ArrayList<>();

        URIBuilder builder = new URIBuilder(url.replaceAll(" ", "%20"));
        String response = constructUrlRequest(builder.build().toString());

        JSONObject responseJson = new JSONObject(response);

        if(responseJson.get("status").toString() == "fail"){
            return null;
        }

        JSONArray data = new JSONArray(responseJson.get("data").toString());

        for (Object obj: data){
            JSONObject jsonObj = (JSONObject) obj;
            countries.add(jsonObj.get("country").toString());
        }

        return countries;
    }

    public City getCityData(String country, String state, String city) throws IOException, URISyntaxException {
        
        City cityFromCache = cache.getCityFromCache(city);

        if(cityFromCache != null){
            return cityFromCache;
        }

        String url = URL + "city?city=" + city +  "&state=" + state + "&country=" + country;
        URIBuilder builder = new URIBuilder(url.replaceAll(" ", "%20"));
        String response = constructUrlRequest(builder.build().toString());

        JSONObject responseJson =  new JSONObject(response);
        System.out.println(response);

        if(responseJson.get("status").toString() == "fail"){

            return null;
            
        }

        else{

            //city data
            JSONObject data = new JSONObject(responseJson.get("data").toString());

            //current
            JSONObject current = new JSONObject(data.get("current").toString());

            //weather
            JSONObject weatherJson = new JSONObject(current.get("weather").toString());
            String timeStamp = weatherJson.get("ts").toString();
            Double temperature = Double.parseDouble(weatherJson.get("tp").toString());
            Double pressure = Double.parseDouble(weatherJson.get("pr").toString());
            Double humidity = Double.parseDouble(weatherJson.get("hu").toString());
            Double windSpeed = Double.parseDouble(weatherJson.get("ws").toString());

            Weather weather = new Weather(temperature, pressure, humidity, windSpeed);

            //pollution
            JSONObject pollutionJson = new JSONObject(current.get("pollution").toString());
            Double aqius = Double.parseDouble(pollutionJson.get("aqius").toString());
            String mainus = pollutionJson.get("mainus").toString();
            Double aqicn = Double.parseDouble(pollutionJson.get("aqicn").toString());
            String maincn = pollutionJson.get("maincn").toString();

            //ArrayList<Polluent> polluents = new ArrayList<Polluent>();

            ////P10
            //JSONObject p10_metrics = new JSONObject(pollutionJson.get("p1").toString());
            //Double p10_conc = Double.parseDouble(p10_metrics.getString("conc").toString());
            //Double p10_aqius = Double.parseDouble(p10_metrics.get("aqius").toString());
            //Double p10_aqicn = Double.parseDouble(p10_metrics.get("aqicn").toString());

            ////CO
            //JSONObject co_metrics = new JSONObject(pollutionJson.get("co").toString());
            //Double co_conc = Double.parseDouble(co_metrics.getString("conc").toString());
            //Double co_aqius = Double.parseDouble(co_metrics.get("aqius").toString());
            //Double co_aqicn = Double.parseDouble(co_metrics.get("aqicn").toString());

            ////NO2
            //JSONObject no2_metrics = new JSONObject(pollutionJson.get("n2").toString());
            //Double no2_conc = Double.parseDouble(no2_metrics.getString("conc").toString());
            //Double no2_aqius = Double.parseDouble(no2_metrics.get("aqius").toString());
            //Double no2_aqicn = Double.parseDouble(no2_metrics.get("aqicn").toString());

            ////O3
            //JSONObject o3_metrics = new JSONObject(pollutionJson.get("o3").toString());
            //Double o3_conc = Double.parseDouble(o3_metrics.getString("conc").toString());
            //Double o3_aqius = Double.parseDouble(o3_metrics.get("aqius").toString());
            //Double o3_aqicn = Double.parseDouble(o3_metrics.get("aqicn").toString());


            //Polluent p10 = new Polluent("p10", p10_conc, p10_aqius, p10_aqicn);
            //polluents.add(p10);

            //Polluent co = new Polluent("co", co_conc, co_aqius, co_aqicn);
            //polluents.add(co);

            //Polluent no2 = new Polluent("no2", no2_conc, no2_aqius, no2_aqicn);
            //polluents.add(no2);

            //Polluent o3 = new Polluent("o3", o3_conc, o3_aqius, o3_aqicn);
            //polluents.add(o3);
            

            AirQuality airQuality = new AirQuality(aqius, aqicn, mainus, maincn);

            City fullCity = new City(city, state, country, timeStamp, weather, airQuality);

            cache.addValue(city, fullCity);

            return fullCity;
        }
    }

    public String constructUrlRequest(String url) throws IOException {
        HttpGet get = new HttpGet(url + "&key=" + PRIVATEKEY);
        CloseableHttpResponse response = this.client.execute(get);

        if (response != null) {
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        }
        else{
            return null;
        }

    }

    public Map<String, Integer> getCacheDetails(){
        HashMap<String, Integer> response = new HashMap<>();
        response.put("hits", this.cache.getHits());
        response.put("misses", this.cache.getMisses());
        response.put("requests", this.cache.getRequests());
        return response;
    }
}
