package com.Tzj.lost_found_system.service;

import com.Tzj.lost_found_system.pojo.Contact;
import com.Tzj.lost_found_system.pojo.PageBean;

public interface ContactService {
    PageBean getAll(Integer page, Integer count);

    PageBean getContact(Integer page, Integer count, Integer id,String content,String contactTime,Integer lostID,Integer userID,Integer itemsUserID);

    int editContact(Contact contact);

    int addContact(Contact contact);

    int deleteContact(Integer id);

    PageBean getFoundContact(Integer page, Integer count, Integer id, String content, String contactTime, Integer userID, Integer foundID,Integer itemsUserID);
}
