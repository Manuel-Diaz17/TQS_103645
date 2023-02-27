package com.example.project.integration;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.project.Connection.TqsBasicHttpClient;
import com.example.project.geocoding.Address;
import com.example.project.geocoding.AddressResolver;

public class AddressResolverIT {

    private TqsBasicHttpClient httpClient;
    private AddressResolver resolver;

    @BeforeEach
    public void init(){
        httpClient = new TqsBasicHttpClient();
        resolver = new AddressResolver(httpClient);
    }

    @Test
    public void whenGoodCoordidates_returnAddress() throws IOException, URISyntaxException, ParseException {

        //todo
        // repeat the same tests conditions from AddressResolverTest, without mocks

        Optional<Address> res = resolver.findAddressForLocation(40.633116,-8.658784);
        assertEquals(res.get(), new Address( "Avenida João Jacinto de Magalhães", "Aveiro", "", "3810-149", null) );
        assertTrue(res.isPresent());
        assertEquals(res.get().city(), "");
        assertEquals(res.get().houseNumber(), null);
        assertEquals(res.get().road(), "Avenida João Jacinto de Magalhães");
        assertEquals(res.get().state(), "Aveiro");
        assertEquals(res.get().zip(), "3810-149");

    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddrress() throws IOException, URISyntaxException, ParseException {

        //todo
        // repeat the same tests conditions from AddressResolverTest, without mocks
        assertThrows(IllegalArgumentException.class, () -> {resolver.findAddressForLocation(-361,-361).get();});
        
    }

}
