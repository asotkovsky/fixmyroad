package com.techelevator.controller;

import com.techelevator.dao.LocationDao;
import com.techelevator.dao.PotholeDao;
import com.techelevator.model.Pothole;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class PotholeController {

    private final PotholeDao potholeDao;
    private final LocationDao locationDao;

    public PotholeController(PotholeDao potholeDao, LocationDao locationDao) {
        this.potholeDao = potholeDao;
        this.locationDao = locationDao;
    }

    @RequestMapping(path = "/potholes", method = RequestMethod.GET)
    public List<Pothole> getAllPotholes() {
        return potholeDao.getAllPotholes();
    }

    @RequestMapping(path = "/location", method = RequestMethod.GET)
    public String getLocationInfo(@RequestParam double latitude, @RequestParam double longitude){
        return locationDao.getFromCoordinates(latitude, longitude);
    }
    @RequestMapping(path = "/potholes", method = RequestMethod.POST)
    public Pothole createPothole(@Valid @RequestBody Pothole pothole){

        return potholeDao.createPothole(pothole);
    }

}
