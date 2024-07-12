package com.Tzj.lost_found_system.service;

import com.Tzj.lost_found_system.pojo.FeedBack;
import com.Tzj.lost_found_system.pojo.PageBean;

import java.util.List;

public interface FeedBackService {
    PageBean getAll(Integer page, Integer count);

    PageBean getFeedBack(Integer page, Integer count, Integer id,String content,String released,Integer userID);

    int addFeedBack(FeedBack feedBack);

    int editFeedBack(FeedBack feedBack);

    int deleteFeedBack(Integer id);

    List<Integer> getData();

    List<String> getDate();
}
