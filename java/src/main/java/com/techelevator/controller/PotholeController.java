package com.techelevator.controller;

import com.techelevator.dao.EmployeeDao;
import com.techelevator.dao.LocationDao;
import com.techelevator.dao.PotholeDao;
import com.techelevator.model.Employee;
import com.techelevator.model.Pothole;
import com.techelevator.model.Status;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
public class PotholeController {

    private final PotholeDao potholeDao;
    private final LocationDao locationDao;
    private final EmployeeDao employeeDao;

    public PotholeController(PotholeDao potholeDao, LocationDao locationDao, EmployeeDao employeeDao) {
        this.potholeDao = potholeDao;
        this.locationDao = locationDao;
        this.employeeDao = employeeDao;
    }
    @PreAuthorize("permitAll")
    @RequestMapping(path = "/potholes", method = RequestMethod.GET)
    public List<Pothole> getAllPotholes() {
        return potholeDao.getAllPotholes();
    }

    @RequestMapping(path = "/location", method = RequestMethod.GET)
    public String getLocationInfo(@RequestParam double latitude, @RequestParam double longitude) {
        return locationDao.getFromCoordinates(latitude, longitude);
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @RequestMapping(path = "/potholes", method = RequestMethod.POST)
    public Pothole createPothole(@Valid @RequestBody Pothole pothole, Principal principal) {
        return potholeDao.createPothole(pothole, principal.getName());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @RequestMapping(path = "/pothole/{pothole_id}/statuses", method = RequestMethod.POST)
    public void createStatus(@PathVariable("pothole_id") int potholeId, @RequestBody Status status, Principal principal) {
        potholeDao.createStatus(potholeId, status, principal.getName());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/potholes/{id}", method = RequestMethod.DELETE)
    public void deletePothole(@PathVariable("id") int id) {
        potholeDao.deletePothole(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/employees", method = RequestMethod.GET)
    public List<Employee> getEmployees ()

    {
        List<Employee> employeeList = employeeDao.gitAllEmployee();
        return employeeList;}
}

