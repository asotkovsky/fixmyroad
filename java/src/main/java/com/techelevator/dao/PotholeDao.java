package com.techelevator.dao;

import com.techelevator.model.Pothole;

import java.util.List;

public interface PotholeDao {
    List<Pothole> getAllPotholes();
    public Pothole createPothole (Pothole pothole);


}
