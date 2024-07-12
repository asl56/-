package com.Tzj.lost_found_system.service;

import com.Tzj.lost_found_system.pojo.PageBean;
import com.Tzj.lost_found_system.pojo.User;

public interface UserService {
    PageBean getAll(Integer page, Integer count);

    PageBean getUser(Integer id, String userName, String password, String name, String role, String phone, String email,Integer page,Integer count,Integer statusID);

    int editUser(User user);

    int addUser(User user);

    int deleteUpdate(Integer id);
}
