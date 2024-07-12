package com.Tzj.lost_found_system.service.impl;

import com.Tzj.lost_found_system.mapper.ContactMapper;
import com.Tzj.lost_found_system.pojo.Contact;
import com.Tzj.lost_found_system.pojo.PageBean;
import com.Tzj.lost_found_system.service.ContactService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactImpl implements ContactService {
    @Autowired
    private ContactMapper contactMapper;
    @Override
    public PageBean getAll(Integer page, Integer count) {
        PageHelper.startPage(page,count);
        List<Contact> list = contactMapper.getAll(page,count);
        Page<Contact> page1= (Page<Contact>) list;
        PageBean pageBean = new PageBean(page1.getTotal(),page1.getResult());
        return pageBean;
    }

    @Override
    public PageBean getContact(Integer page, Integer count,Integer id,String content,String contactTime,Integer lostID,Integer userID,Integer itemsUserID) {
        PageHelper.startPage(page,count);
        List<Contact> list = contactMapper.getContact(page,count,id,content,contactTime,lostID,userID,itemsUserID);
        Page<Contact> page1= (Page<Contact>) list;
        PageBean pageBean = new PageBean(page1.getTotal(),page1.getResult());
        return pageBean;
    }

    @Override
    public int editContact(Contact contact) {
        return contactMapper.editContact(contact);
    }

    @Override
    public int addContact(Contact contact) {
        return contactMapper.addContact(contact);
    }

    @Override
    public int deleteContact(Integer id) {
        return contactMapper.deleteContact(id);
    }

    @Override
    public PageBean getFoundContact(Integer page, Integer count, Integer id, String content, String contactTime, Integer userID, Integer foundID,Integer itemsUserID) {
        PageHelper.startPage(page,count);
        List<Contact> list = contactMapper.getFoundContact(page,count,id,content,contactTime,userID,foundID,itemsUserID);
        Page<Contact> page1= (Page<Contact>) list;
        PageBean pageBean = new PageBean(page1.getTotal(),page1.getResult());
        return pageBean;
    }
}
