package com.techelevator.dao;

import com.techelevator.model.Pothole;
import com.techelevator.model.Status;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

public interface PotholeDao {
    List<Pothole> getAllPotholes();
    public void addImageToPothole(Pothole pothole, String url);

    public Pothole createPothole (Pothole pothole, String username);

    public void deletePothole (int id);

    public List<Status> getPotholeStatuses(int potholeId);

    public void createStatus(int potholeId, Status status, String username);

    public void deleteStatus(int statusId);
}
