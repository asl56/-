package com.Tzj.lost_found_system.service;

import com.Tzj.lost_found_system.pojo.User;


public interface LoginService {
    User login(String userName, String password);
}
