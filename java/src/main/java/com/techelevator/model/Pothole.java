package com.techelevator.model;

import javax.validation.constraints.AssertTrue;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class Pothole {

    private int id;
    private double longitude;
    private double latitude;
    private String description;
    private int severity;
    private String locationOnRoadway;
    private String roadName;
    private String neighborhood;
    private String city;
    private String state;
    private List<Status> statuses;
    private Status currentStatus;
    private boolean isUserFavorite;
    private List<String> imageUrl;


    @AssertTrue(message = "Latitude and Longitude must be provided")
    public boolean hasValidLatLng() {
        return (this.latitude != 0) && (this.longitude != 0);
    }

    @Override
    public String toString() {
        return "Pothole{" +
               "id=" + id +
               ", longitude=" + longitude +
               ", latitude=" + latitude +
               ", description='" + description + '\'' +
               ", severity=" + severity +
               ", locationOnRoadway='" + locationOnRoadway + '\'' +
               ", roadName='" + roadName + '\'' +
               ", neighborhood='" + neighborhood + '\'' +
               ", city='" + city + '\'' +
               ", state='" + state + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pothole pothole = (Pothole) o;
        return id == pothole.id &&
               Double.compare(pothole.longitude, longitude) == 0 &&
               Double.compare(pothole.latitude, latitude) == 0 &&
               severity == pothole.severity &&
               Objects.equals(description, pothole.description) &&
               Objects.equals(locationOnRoadway, pothole.locationOnRoadway) &&
               Objects.equals(roadName, pothole.roadName) &&
               Objects.equals(neighborhood, pothole.neighborhood) &&
               Objects.equals(city, pothole.city) &&
               Objects.equals(state, pothole.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                longitude,
                latitude,
                description,
                severity,
                locationOnRoadway,
                roadName,
                neighborhood,
                city,
                state
                           );
    }

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

    public Status getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Status currentStatus) {
        this.currentStatus = currentStatus;
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

    public boolean isUserFavorite() {
        return isUserFavorite;
    }

    public void setUserFavorite(boolean userFavorite) {
        isUserFavorite = userFavorite;
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
        if (description == null) {
            this.description = description;
        } else if (description.length() > 200) {
            this.description = description.substring(0, 200);
        } else {
            this.description = description;
        };


    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocationOnRoadway() {
        return locationOnRoadway;
    }

    public void setLocationOnRoadway(String locationOnRoadway) {
        this.locationOnRoadway = locationOnRoadway;
    }

    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }

}
