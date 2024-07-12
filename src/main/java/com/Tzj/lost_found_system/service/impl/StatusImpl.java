package com.Tzj.lost_found_system.service.impl;

import com.Tzj.lost_found_system.mapper.StatusMapper;
import com.Tzj.lost_found_system.pojo.Status;
import com.Tzj.lost_found_system.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StatusImpl implements StatusService {
    @Autowired
    private StatusMapper statusMapper;

    @Override
    public List<Status> getAll() {
        return statusMapper.getAll();
    }

    @Override
    public List<Status> getStatus(int id, String status) {
        return statusMapper.getStatus(id,status);
    }

    @Override
    public List<Status> getLostStatus() {
        return statusMapper.getLostStatus();
    }

    @Override
    public List<Status> getFoundStatus() {
        return statusMapper.getFoundStatus();
    }

    @Override
    public List<Status> getUserStatus() {
        return statusMapper.getUserStatus();
    }
}
