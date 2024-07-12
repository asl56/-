package com.Tzj.lost_found_system.service.impl;

import com.Tzj.lost_found_system.mapper.FoundMapper;
import com.Tzj.lost_found_system.pojo.EchartsData;
import com.Tzj.lost_found_system.pojo.Found;
import com.Tzj.lost_found_system.pojo.PageBean;
import com.Tzj.lost_found_system.service.FoundService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoundImpl implements FoundService {
    @Autowired
    private FoundMapper foundMapper;
    @Override
    public PageBean getFoundAll(Integer page, Integer count) {
        PageHelper.startPage(page,count);
        List<Found> list = foundMapper.getFoundAll(page,count);
        Page<Found> page1= (Page<Found>) list;
        PageBean pageBean = new PageBean(page1.getTotal(),page1.getResult());
        return pageBean;
    }

    @Override
    public PageBean getFound(Integer page, Integer count, Integer id, String title, String description, String phone, String userID, String releaseDate, Integer statusID) {
        PageHelper.startPage(page,count);
        List<Found> list = foundMapper.getFound(page,count,id,title,description,phone,userID,releaseDate,statusID);
        Page<Found> page1= (Page<Found>) list;
        PageBean pageBean = new PageBean(page1.getTotal(),page1.getResult());
        return pageBean;
    }

    @Override
    public int editFound(Found found) {
        return foundMapper.editFound(found);
    }

    @Override
    public int addFound(Found found) {
        return foundMapper.addFound(found);
    }

    @Override
    public int deleteFound(Integer id) {
        return foundMapper.deleteFound(id);
    }

    @Override
    public List<EchartsData> getFoundData() {
        return foundMapper.getFoundData();
    }
}
