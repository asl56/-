package com.Tzj.lost_found_system.service.impl;

import com.Tzj.lost_found_system.mapper.UserMapper;
import com.Tzj.lost_found_system.pojo.PageBean;
import com.Tzj.lost_found_system.pojo.User;
import com.Tzj.lost_found_system.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public PageBean getAll(Integer page,Integer count) {
        PageHelper.startPage(page,count);
        List<User> list = userMapper.getAll(page,count);
        Page<User> page1= (Page<User>) list;
        PageBean pageBean = new PageBean(page1.getTotal(),page1.getResult());
        return pageBean;

    }

    @Override
    public PageBean getUser(Integer id, String userName, String password, String name, String role, String phone, String email,Integer page,Integer count,Integer statusID ) {
        PageHelper.startPage(page,count);
        List<User> list = userMapper.getUser(id,userName,password,name,role,phone,email,page,count,statusID);
        Page<User> page1= (Page<User>) list;
        PageBean pageBean = new PageBean(page1.getTotal(),page1.getResult());
        return pageBean;
    }

    @Override
    public int editUser(User user) {
        return userMapper.editUser(user);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int deleteUpdate(Integer id) {
        return userMapper.deleteUser(id);
    }
}
