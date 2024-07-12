package com.Tzj.lost_found_system.service;

import com.Tzj.lost_found_system.pojo.Status;

import java.util.List;

public interface StatusService {

    List<Status> getAll();

    List<Status> getStatus(int id, String status);

    List<Status> getLostStatus();

    List<Status> getFoundStatus();

    List<Status> getUserStatus();
}
