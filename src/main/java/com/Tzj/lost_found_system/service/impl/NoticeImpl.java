package com.Tzj.lost_found_system.service.impl;

import com.Tzj.lost_found_system.mapper.NoticeMapper;
import com.Tzj.lost_found_system.pojo.Notice;
import com.Tzj.lost_found_system.pojo.PageBean;
import com.Tzj.lost_found_system.service.NoticeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;
    @Override
    public PageBean getAll(Integer page, Integer count) {
        PageHelper.startPage(page,count);
        List<Notice> list = noticeMapper.getAll(page,count);
        Page<Notice> page1= (Page<Notice>) list;
        PageBean pageBean = new PageBean(page1.getTotal(),page1.getResult());
        return pageBean;

    }

    @Override
    public PageBean getNotice(Integer page,Integer count,Integer id, String title, String releaseDate, Integer userID) {
        PageHelper.startPage(page,count);
        List<Notice> list = noticeMapper.getNotice(page,count,id,title,releaseDate,userID);
        Page<Notice> page1= (Page<Notice>) list;
        PageBean pageBean = new PageBean(page1.getTotal(),page1.getResult());
        return pageBean;
    }

    @Override
    public int editNotice(Notice notice) {
        return noticeMapper.editNotice(notice);

    }

    @Override
    public int addNotice(Notice notice) {
        return noticeMapper.addNotice(notice);
    }

    @Override
    public int deleteNotice(Integer id) {
        return noticeMapper.deleteNotice(id);
    }
}
