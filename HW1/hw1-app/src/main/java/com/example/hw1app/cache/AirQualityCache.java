package com.example.hw1app.cache;

import java.util.HashMap;
import java.util.Map;

import com.example.hw1app.entities.City;

public class AirQualityCache {
    
    private int hits = 0;
    private int misses = 0;
    private int requests = 0;

    private long cleanTime;

    private Map<String, City> cacheMap = new HashMap<>();
    private Map<String, Long> ttl = new HashMap<>();


    public AirQualityCache(int cleanTime) {
        this.cleanTime = cleanTime;
        cleaningByTime();
    }

    public int getHits() {
        return hits;
    }

    public int getMisses() {
        return misses;
    }

    public int getRequests() {
        return requests;
    }

    public boolean addValue(String key, City city){
        long maxTime = System.currentTimeMillis() + this.cleanTime * 1000;
        ttl.put(key, maxTime);
        cacheMap.put(key, city);
        return true;
    }

    public boolean deleteValue(String key){
        if(cacheMap.containsKey(key)){
            cacheMap.remove(key);
            ttl.remove(key);
            return true;
        }
        return false;
    }

    public City getCityFromCache(String key){
        if(cacheMap.containsKey(key) ){
            this.hits++;
            this.requests++;
            return cacheMap.get(key);
        }
        this.requests++;
        this.misses++;
        return null;
    }

    public Thread cleaningByTime(){
        Thread thread = new Thread(){

            @Override
            public void run(){
                while (true){

                    for(String key: cacheMap.keySet()){
                        if(ttl.get(key) < System.currentTimeMillis()){
                            deleteValue(key);
                        }
                    }
                    try {
                        Thread.sleep(cleanTime * 1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }


                }
            }
        };
        thread.start();
        return thread;
    }

    public void clearCache(){
        this.cacheMap.clear();
        this.ttl.clear();
    }

    public int getCacheSize(){
       return this.cacheMap.size();
    }

    public boolean containsCity(String key){
        return this.cacheMap.containsKey(key);
    }

}
