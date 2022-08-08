package com.techelevator.dao;

import com.techelevator.model.Pothole;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPotholeDao implements PotholeDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcPotholeDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate; }

    @Override
    public List<Pothole> getAllPotholes() {
        List<Pothole> potholes = new ArrayList<Pothole>();

        String sql = "SELECT potholes.id, potholes.datetime_reported, potholes.longitude, potholes.latitude, potholes.description, potholes.severity, potholes.location_on_roadway FROM potholes";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            potholes.add (mapRowToPothole(results));
        }
        return potholes;
    }

    private Pothole mapRowToPothole(SqlRowSet results) {
        Pothole pothole = new Pothole();

        pothole.setId( results.getInt("id"));
        pothole.setDatetimeReported( results.getTimestamp("datetime_reported"));
        pothole.setLongitude( results.getDouble("longitude"));
        pothole.setLatitude( results.getDouble("latitude"));
        pothole.setDescription( results.getString("description"));
        pothole.setSeverity( results.getInt("severity"));
        pothole.setLocationOnRoadway( results.getString("location_on_roadway"));

        return pothole;

    }
}
