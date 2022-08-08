package com.techelevator.controller;

import com.techelevator.dao.PotholeDao;
import com.techelevator.model.Pothole;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class PotholeController {

    private final PotholeDao potholeDao;

    public PotholeController(PotholeDao potholeDao) {
        this.potholeDao = potholeDao;
    }

    @RequestMapping(path="/potholes", method= RequestMethod.GET)
    public List<Pothole> getAllPotholes() {
        return potholeDao.getAllPotholes();
    }
}
