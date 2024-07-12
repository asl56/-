package com.Tzj.lost_found_system.controller;

import com.Tzj.lost_found_system.pojo.Result;
import com.Tzj.lost_found_system.pojo.Status;
import com.Tzj.lost_found_system.service.StatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class StatusController {
    @Autowired
    private StatusService statusService;

    @GetMapping("/getStatusAll") //获取所有状态
    public Result getAll() {

        List<Status> statusList = statusService.getAll();
        return Result.success(statusList);
    }

    @GetMapping("/getStatus") //按条件获取状态
    public  Result getStatus(int id,String status) {
        List<Status> statusList = statusService.getStatus(id,status);

        return Result.success(statusList);
    }
    @GetMapping("/getLostStatus")
    public Result getLostStatus(){
        List<Status> statusList = statusService.getLostStatus();
        return Result.success(statusList);
    }
    @GetMapping("/getFoundStatus")
    public Result getFoundStatus(){
        List<Status> statusList = statusService.getFoundStatus();
        return Result.success(statusList);
    }
    @GetMapping("/getUserStatus")
    public Result getUserStatus(){
        List<Status> statusList = statusService.getUserStatus();
        return Result.success(statusList);
    }
}
