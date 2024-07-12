package com.Tzj.lost_found_system.service.impl;

import com.Tzj.lost_found_system.mapper.FeedBackMapper;
import com.Tzj.lost_found_system.pojo.FeedBack;
import com.Tzj.lost_found_system.pojo.PageBean;
import com.Tzj.lost_found_system.service.FeedBackService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedBackImpl implements FeedBackService {
    @Autowired
    private FeedBackMapper feedBackMapper;

    @Override
    public PageBean getAll(Integer page, Integer count) {
        PageHelper.startPage(page,count);
        List<FeedBack> list = feedBackMapper.getAll(page,count);
        Page<FeedBack> page1= (Page<FeedBack>) list;
        PageBean pageBean = new PageBean(page1.getTotal(),page1.getResult());
        return pageBean;
    }

    @Override
    public PageBean getFeedBack(Integer page, Integer count, Integer id,String content,String released,Integer userID) {
        PageHelper.startPage(page,count);
        List<FeedBack> list = feedBackMapper.getFeedBack(page,count,id,content,released,userID);
        Page<FeedBack> page1= (Page<FeedBack>) list;
        PageBean pageBean = new PageBean(page1.getTotal(),page1.getResult());
        return pageBean;
    }

    @Override
    public int addFeedBack(FeedBack feedBack) {
        return feedBackMapper.addFeedBack(feedBack);
    }

    @Override
    public int editFeedBack(FeedBack feedBack) {
        return feedBackMapper.editFeedBack(feedBack);
    }

    @Override
    public int deleteFeedBack(Integer id) {
        return feedBackMapper.deleteFeedBack(id);
    }

    @Override
    public List<Integer> getData() {
        return feedBackMapper.getData();
    }

    @Override
    public List<String> getDate() {
        return feedBackMapper.getDate();
    }
}
