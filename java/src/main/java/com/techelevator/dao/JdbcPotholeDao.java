package com.techelevator.dao;

import com.techelevator.model.Pothole;
import com.techelevator.model.Status;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPotholeDao implements PotholeDao {

    private JdbcTemplate jdbcTemplate;
    private UserDao userDao;

    public JdbcPotholeDao(JdbcTemplate jdbcTemplate, UserDao userDao) {this.jdbcTemplate = jdbcTemplate; this.userDao = userDao;}

    @Override
    public List<Pothole> getAllPotholes() {
        List<Pothole> potholes = new ArrayList<Pothole>();

        String sql = "SELECT potholes.id, potholes.longitude, potholes.latitude, potholes.description, potholes.severity, potholes.location_on_roadway, potholes.road_name, potholes.neighborhood, potholes.city, potholes.state FROM potholes";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            potholes.add (mapRowToPothole(results));
        }
        return potholes;
    }

    @Override
    public Pothole createPothole(Pothole pothole, String username) {
        String sql = "INSERT INTO potholes (longitude, latitude, description, severity, location_on_roadway, road_name, neighborhood, city, state) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?) returning id";
        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, pothole.getLongitude(), pothole.getLatitude(), pothole.getDescription(), pothole.getSeverity(), pothole.getLocationOnRoadway(), pothole.getRoadName(), pothole.getNeighborhood(), pothole.getCity(), pothole.getState());
        pothole .setId(id);

        int userId = userDao.findIdByUsername(username);
        sql = "INSERT INTO pothole_status (pothole_id, status_id, user_id) VALUES " +
                "(?,1,?)";
        jdbcTemplate.update(sql, pothole.getId(),userId);

        return pothole;
    }

    @Override
    public List<Status> getPotholeStatuses(int potholeId) {

        List<Status> statuses = new ArrayList<Status>();

        String sql = "SELECT s.status_name, ps.date " +
                "FROM status s " +
                "JOIN pothole_status ps ON s.id = ps.status_id " +
                "JOIN potholes p ON ps.pothole_id = p.id " +
                "WHERE p.id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, potholeId);

        while (results.next()) {
            statuses.add (mapRowToStatus(results));
        }
        return statuses;
    }
    @Override
    public void createStatus(int potholeId, int statusId, String username) {
        String sql = "INSERT INTO pothole_status (pothole_id, status_id, user_id) " +
                "VALUES(?, ?, ?)";

        int userId = userDao.findIdByUsername(username);
        jdbcTemplate.update(sql, potholeId, statusId, userId);

    }

    private Status mapRowToStatus(SqlRowSet results) {
        Status status = new Status();

        status.setName( results.getString("status_name"));
        status.setDate( results.getDate("date"));
        return status;
    }

    private Pothole mapRowToPothole(SqlRowSet results) {
        Pothole pothole = new Pothole();

        pothole.setId( results.getInt("id"));
        pothole.setLongitude( results.getDouble("longitude"));
        pothole.setLatitude( results.getDouble("latitude"));
        pothole.setDescription( results.getString("description"));
        pothole.setSeverity( results.getInt("severity"));
        pothole.setLocationOnRoadway( results.getString("location_on_roadway"));
        pothole.setRoadName(results.getString("road_name"));
        pothole.setNeighborhood(results.getString("neighborhood"));
        pothole.setCity(results.getString("city"));
        pothole.setState(results.getString("state"));

        return pothole;

    }


}
