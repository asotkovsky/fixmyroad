package com.techelevator.dao;

import com.techelevator.model.Pothole;
import com.techelevator.model.Status;

import java.security.Principal;
import java.util.List;

public interface PotholeDao {
    List<Pothole> getAllPotholes();

    public Pothole createPothole (Pothole pothole, String username);

    public void deletePothole (int id);

    public List<Status> getPotholeStatuses(int potholeId);
    public void createStatus(int potholeId, int statusId, String username);
}
