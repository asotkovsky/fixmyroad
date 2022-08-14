package com.techelevator.controller;

import com.techelevator.dao.LocationDao;
import com.techelevator.dao.PotholeDao;
import com.techelevator.model.Pothole;
import com.techelevator.model.Status;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
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
    public List<Pothole> getAllPotholes(Principal principal) {
        return potholeDao.getAllPotholes(principal.getName());
    }

    @RequestMapping(path = "/location", method = RequestMethod.GET)
    public String getLocationInfo(@RequestParam double latitude, @RequestParam double longitude) {
        return locationDao.getFromCoordinates(latitude, longitude);
    }

    @RequestMapping(path = "/pothole/{id}/statuses", method = RequestMethod.GET)
    public List<Status> getPotholeStatuses(@PathVariable("id") int potholeId) {
        return potholeDao.getPotholeStatuses(potholeId);
    }

    @RequestMapping(path = "/potholes", method = RequestMethod.POST)
    public Pothole createPothole(@Valid @RequestBody Pothole pothole, Principal principal) {
        return potholeDao.createPothole(pothole, principal.getName());
    }

    @RequestMapping(path = "/pothole/{pothole_id}/statuses", method = RequestMethod.POST)
    public void createStatus(@PathVariable("pothole_id") int potholeId, @RequestBody Status status, Principal principal) {
        potholeDao.createStatus(potholeId, status, principal.getName());
    }

    @RequestMapping(path = "/potholes/{id}", method = RequestMethod.DELETE)
    public void deletePothole(@PathVariable("id") int id) {
        potholeDao.deletePothole(id);
    }

}
