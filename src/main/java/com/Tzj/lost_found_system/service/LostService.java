package com.Tzj.lost_found_system.service;

import com.Tzj.lost_found_system.pojo.EchartsData;
import com.Tzj.lost_found_system.pojo.Lost;
import com.Tzj.lost_found_system.pojo.PageBean;

import java.util.List;

public interface LostService {
    PageBean getLostAll(Integer page, Integer count);

    PageBean getLost(Integer page, Integer count, Integer id, String title, String description, String phone, String userID, String releaseDate, Integer statusID);

    int editLost(Lost lost);

    int addLost(Lost lost);

    int deleteLost(Integer id);

    List<EchartsData> getLostData();
}
