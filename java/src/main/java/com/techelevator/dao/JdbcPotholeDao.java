package com.techelevator.dao;

import com.techelevator.model.Pothole;
import com.techelevator.model.Status;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPotholeDao implements PotholeDao {

    private JdbcTemplate jdbcTemplate;
    private UserDao userDao;

    public JdbcPotholeDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
    }

    @Override
    public List<Pothole> getAllPotholes() {

        List<Pothole> potholes = new ArrayList<Pothole>();

        String sql =
                "SELECT potholes.id, potholes.longitude,potholes.latitude, potholes.description, potholes.severity, potholes.location_on_roadway, " +
                        "potholes.road_name, potholes.neighborhood, potholes.city, potholes.state " +
                        "FROM potholes " +
                        "JOIN pothole_status ps ON potholes.id = ps.pothole_id " +
                        "ORDER BY ps.date, ps.status_id";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            potholes.add(mapRowToPothole(results));
        }
        return potholes;
    }

    @Override
    public Pothole createPothole(Pothole pothole, String username) {
        String sql =
                "INSERT INTO potholes (" +
                "longitude, " +
                "latitude, " +
                "description, " +
                "severity, " +
                "location_on_roadway, " +
                "road_name, " +
                "neighborhood, " +
                "city, " +
                "state" +
                ") " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?) returning id";
        Integer id = jdbcTemplate.queryForObject(sql, Integer.class,
                pothole.getLongitude(),
                pothole.getLatitude(),
                pothole.getDescription(),
                pothole.getSeverity(),
                pothole.getLocationOnRoadway(),
                pothole.getRoadName(),
                pothole.getNeighborhood(),
                pothole.getCity(),
                pothole.getState());
                pothole.setId(id);

        int userId = userDao.findIdByUsername(username);
        sql =
                "INSERT INTO pothole_status (pothole_id, status_id, user_id) VALUES " +
                "(?,1,?)";
        jdbcTemplate.update(sql, pothole.getId(), userId);
        for(String url: pothole.getImageUrl()){
            addImageToPothole(pothole, url);
        }
        return pothole;
    }
    @Override
    public void addImageToPothole(Pothole pothole, String url){
        String sql = "insert into image_url(url, pothole_id) values (?, ?) ";
        jdbcTemplate.update(sql, url, pothole.getId());


    }

    @Override
    public List<Status> getPotholeStatuses(int potholeId) {

        List<Status> statuses = new ArrayList<Status>();

        String sql = "SELECT s.status_name, ps.date, u.username as email, ps.id, s.is_public " +
                "FROM status s " +
                "JOIN pothole_status ps ON s.id = ps.status_id " +
                "JOIN potholes p ON ps.pothole_id = p.id " +
                "Join users u on u.user_id = ps.user_id " +
                "WHERE p.id = ? " +
                "order by ps.date ";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, potholeId);

        while (results.next()) {
            statuses.add(mapRowToStatus(results));
        }
        return statuses;
    }


    private List<String> getImages (int potholeId){
        List<String> url = new ArrayList<String>();
        String sql = "select url from image_url where pothole_id = ? ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, potholeId);
        while (results.next()){
            url.add(results.getString("url"));
        }
            return url;

    }

    @Override
    public void createStatus(int potholeId, Status status, String username) {
        String sql =
                "INSERT INTO pothole_status (pothole_id, status_id, user_id, date) " +
                "VALUES(?, ?, ?, ?)";

        int userId = userDao.findIdByUsername(username);
        jdbcTemplate.update(sql, potholeId, status.getId(), userId, status.getDate());

    }

    @Override
    public void deleteStatus(int statusId) {
        String sql = "DELETE from pothole_status WHERE id = ?";
        jdbcTemplate.update(sql, statusId);
    }

    private Status mapRowToStatus(SqlRowSet results) {
        Status status = new Status();

        status.setName(results.getString("status_name"));
        Date date = results.getDate("date");
        status.setEmail(results.getString("email"));
        status.setId(results.getInt("id"));
        status.setPublic(results.getBoolean("is_public"));
        if (date != null) {
            status.setDate(date.toLocalDate());
        }
        return status;
    }

    private Pothole mapRowToPothole(SqlRowSet results) {
        Pothole pothole = new Pothole();

        pothole.setId(results.getInt("id"));
        pothole.setLongitude(results.getDouble("longitude"));
        pothole.setLatitude(results.getDouble("latitude"));
        pothole.setDescription(results.getString("description"));
        pothole.setSeverity(results.getInt("severity"));
        pothole.setLocationOnRoadway(results.getString("location_on_roadway"));
        pothole.setRoadName(results.getString("road_name"));
        pothole.setNeighborhood(results.getString("neighborhood"));
        pothole.setCity(results.getString("city"));
        pothole.setState(results.getString("state"));
        List<Status> potholeStatus = getPotholeStatuses(pothole.getId());
        pothole.setStatuses(potholeStatus);
        pothole.setCurrentStatus(potholeStatus.get(potholeStatus.size()-1));
        pothole.setImageUrl(getImages(pothole.getId()));
        return pothole;

    }

    public void deletePothole(int id) {
        String sql = "DELETE from pothole_status WHERE pothole_id = ?";
        jdbcTemplate.update(sql, id);
        sql = "DELETE from image_url WHERE pothole_id = ?";
        jdbcTemplate.update(sql, id);
        sql = "DELETE from potholes WHERE id = ?";
        jdbcTemplate.update(sql, id);

    }

}
