package com.techelevator.dao;

import com.techelevator.model.Pothole;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class JdbcPotholeDaoTests extends BaseDaoTests {

    private Pothole POTHOLE_1;
    private Pothole POTHOLE_2;
    private JdbcPotholeDao potholeDao;

    @Before
    public void setUp() {
        POTHOLE_1 = new Pothole();
        POTHOLE_2 = new Pothole();
        JdbcTemplate template = new JdbcTemplate(this.dataSource);
        UserDao userDao = new JdbcUserDao(template);
        potholeDao = new JdbcPotholeDao(template, userDao);
        POTHOLE_1.setId(1);
        POTHOLE_1.setState("OH");
        POTHOLE_1.setCity("Columbus");
        POTHOLE_1.setNeighborhood("Linden");
        POTHOLE_1.setRoadName("Cleveland Ave");
        POTHOLE_1.setLocationOnRoadway("shoulder");
        POTHOLE_1.setSeverity(5);
        POTHOLE_1.setDescription("pothole 1");
        POTHOLE_1.setLongitude(-90);
        POTHOLE_1.setLatitude(12);

        POTHOLE_2.setId(2);
        POTHOLE_2.setState("OH");
        POTHOLE_2.setCity("Columbus");
        POTHOLE_2.setNeighborhood("Linden");
        POTHOLE_2.setRoadName("Cleveland Ave");
        POTHOLE_2.setLocationOnRoadway("road");
        POTHOLE_2.setSeverity(3);
        POTHOLE_2.setDescription("pothole 2");
        POTHOLE_2.setLongitude(10);
        POTHOLE_2.setLatitude(6);

    }

    @Test
    public void getListPotholes() {
        List<Pothole> expected = new ArrayList<>();
        expected.add(POTHOLE_1);
        expected.add(POTHOLE_2);
        List<Pothole> actual = potholeDao.getAllPotholes();
        Assert.assertEquals(expected,actual);

    }
    @Test
    public void addPotholeReturnsNewPothole(){
       Pothole pothole = new Pothole();
       String username = "user1";
       pothole.setState("ZZ");
       Pothole createdPothole = potholeDao.createPothole(pothole, username);
       Assert.assertEquals(createdPothole.getId(),3);
       Assert.assertEquals(pothole, createdPothole);

    }
    @Test
    public void addPotholeCreatesInDataBase(){
        Pothole pothole = new Pothole();
        String username = "user1";
        potholeDao.createPothole(pothole, username);
        List<Pothole> potholeList = potholeDao.getAllPotholes();
        Assert.assertEquals(potholeList.size(),3);
    }


}
