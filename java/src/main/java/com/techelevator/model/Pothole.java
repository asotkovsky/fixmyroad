package com.techelevator.model;

import java.sql.Timestamp;

public class Pothole {

    private int id;
    private Timestamp datetimeReported;
    private double longitude;
    private double latitude;
    private String description;
    private int severity;
    private String locationOnRoadway;
    private String roadName;
    private String neighborhood;
    private String city;
    private String state;

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDatetimeReported() {
        return datetimeReported;
    }

    public void setDatetimeReported(Timestamp datetimeReported) {
        this.datetimeReported = datetimeReported;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public String getLocationOnRoadway() {
        return locationOnRoadway;
    }

    public void setLocationOnRoadway(String locationOnRoadway) {
        this.locationOnRoadway = locationOnRoadway;
    }
}
