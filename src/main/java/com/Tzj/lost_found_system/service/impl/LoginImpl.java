package com.Tzj.lost_found_system.service.impl;

import com.Tzj.lost_found_system.mapper.LoginMapper;
import com.Tzj.lost_found_system.pojo.User;
import com.Tzj.lost_found_system.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Override
    public User login(String userName, String password) {
        return loginMapper.login(userName,password);
    }
}
