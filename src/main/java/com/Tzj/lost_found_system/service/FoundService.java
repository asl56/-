package com.Tzj.lost_found_system.service;

import com.Tzj.lost_found_system.pojo.EchartsData;
import com.Tzj.lost_found_system.pojo.Found;
import com.Tzj.lost_found_system.pojo.PageBean;

import java.util.List;

public interface FoundService {
    PageBean getFoundAll(Integer page, Integer count);

    PageBean getFound(Integer page, Integer count, Integer id, String title, String description, String phone, String userID, String releaseDate, Integer statusID);

    int editFound(Found lost);

    int addFound(Found lost);

    int deleteFound(Integer id);

    List<EchartsData> getFoundData();
}
