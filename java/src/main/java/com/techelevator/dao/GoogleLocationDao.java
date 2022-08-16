package com.techelevator.dao;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class GoogleLocationDao implements LocationDao{
    private RestTemplate restTemplate = new RestTemplate();
    private String baseURL = "https://maps.googleapis.com/maps/api/geocode/json?key=AIzaSyC2q2s0usO82lGaQTribBQWKObAHhv_iAU";

    @Override
    public String getFromCoordinates(double latitude, double longitude) {
        String url = baseURL + "&latlng=" + latitude + "," + longitude;
        return restTemplate.getForObject(url, String.class);

    }


}
