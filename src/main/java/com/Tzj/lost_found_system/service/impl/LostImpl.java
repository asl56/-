package com.Tzj.lost_found_system.service.impl;

import com.Tzj.lost_found_system.mapper.LostMapper;
import com.Tzj.lost_found_system.pojo.EchartsData;
import com.Tzj.lost_found_system.pojo.Lost;
import com.Tzj.lost_found_system.pojo.PageBean;
import com.Tzj.lost_found_system.service.LostService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LostImpl implements LostService {
    @Autowired
    private LostMapper lostMapper;
    @Override
    public PageBean getLostAll(Integer page, Integer count) {
        PageHelper.startPage(page,count);
        List<Lost> list = lostMapper.getLostAll(page,count);
        Page<Lost> page1= (Page<Lost>) list;
        PageBean pageBean = new PageBean(page1.getTotal(),page1.getResult());
        return pageBean;
    }

    @Override
    public PageBean getLost(Integer page, Integer count, Integer id, String title, String description, String phone, String userID, String releaseDate, Integer statusID) {
        PageHelper.startPage(page,count);
        List<Lost> list = lostMapper.getLost(page,count,id,title,description,phone,userID,releaseDate,statusID);
        Page<Lost> page1= (Page<Lost>) list;
        PageBean pageBean = new PageBean(page1.getTotal(),page1.getResult());
        return pageBean;
    }

    @Override
    public int editLost(Lost lost) {
        return lostMapper.editLost(lost);
    }

    @Override
    public int addLost(Lost lost) {
        return lostMapper.addLost(lost);
    }

    @Override
    public int deleteLost(Integer id) {
        return lostMapper.deleteLost(id);
    }

    @Override
    public List<EchartsData> getLostData() {
        return lostMapper.getLostData();
    }
}
