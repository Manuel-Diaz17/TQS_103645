package com.example.project;

import java.util.Optional;

public class AddressResolver {
    
    private final ISimpleHttpClient httpClient;

    public AddressResolver(ISimpleHttpClient httpClient){
        this.httpClient = httpClient;
    }

    public Optional<Address> findAddressForLocation(Double x, Double y){
        return null;
    }
}
