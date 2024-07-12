package com.Tzj.lost_found_system.controller;

import com.Tzj.lost_found_system.pojo.PageBean;
import com.Tzj.lost_found_system.pojo.Result;
import com.Tzj.lost_found_system.pojo.User;
import com.Tzj.lost_found_system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUserAll") //获取所有用户
    public Result getAll(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "5") Integer count) {
        PageBean users = userService.getAll(page,count);
        return Result.success(users);
    }

    @GetMapping("/getUser") //按条件查询用户
    public Result getUser(Integer id, String userName, String password, String name, String role, String phone, String email,@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "5") Integer count,Integer statusID) {
        PageBean users = userService.getUser(id, userName, password, name, role, phone, email,page,count,statusID);
        return Result.success(users);
    }

    @PostMapping("/editUser") //修改用户
    public int editUser(@RequestBody User user) {
        log.info("user"+user.toString());
        return userService.editUser(user);
    }

    @PostMapping("/addUser") //添加用户
    public int addUser(@RequestBody User user){
        return userService.addUser(user);
    }
    @GetMapping("/deleteUser")  //删除用户
    public int deleteUser(Integer id){
        return userService.deleteUpdate(id);
    }
}
