package com.Tzj.lost_found_system.service;

import com.Tzj.lost_found_system.pojo.Notice;
import com.Tzj.lost_found_system.pojo.PageBean;

public interface NoticeService {

    PageBean getAll(Integer page,Integer count);

    PageBean getNotice(Integer page, Integer count, Integer id, String title, String releaseDate, Integer userID);

    int editNotice(Notice notice);

    int addNotice(Notice notice);

    int deleteNotice(Integer id);
}
