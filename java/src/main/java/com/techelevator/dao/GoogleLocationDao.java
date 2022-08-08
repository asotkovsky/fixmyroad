package com.techelevator.dao;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class GoogleLocationDao implements LocationDao{
    private RestTemplate restTemplate = new RestTemplate();
    private String baseURL = "https://maps.googleapis.com/maps/api/geocode/json?key=AIzaSyA1vYqGV9VBgHrGW2HSWjjU0PsE448kAec";

    @Override
    public String getFromCoordinates(double latitude, double longitude) {
        String url = baseURL + "&latlng=" + latitude + "," + longitude;
        return restTemplate.getForObject(url, String.class);

    }


}
