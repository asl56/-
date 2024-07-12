package com.Tzj.lost_found_system.controller;

import com.Tzj.lost_found_system.pojo.FeedBack;
import com.Tzj.lost_found_system.pojo.PageBean;
import com.Tzj.lost_found_system.pojo.Result;
import com.Tzj.lost_found_system.service.FeedBackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
public class FeedBackController {
    @Autowired
    private FeedBackService feedBackService;

    @GetMapping("/getFeedBackAll") //获取用户留言
    public Result getAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer count){
        PageBean feedBacks = feedBackService.getAll(page,count);
        return Result.success(feedBacks);
    }
    @GetMapping("/getFeedBack")
    public Result getFeedBack(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5")Integer count,Integer id,String content,String released,Integer userID){

        PageBean feedBacks = feedBackService.getFeedBack(page,count,id,content,released,userID);
        return Result.success(feedBacks);
    }
    @PostMapping("/addFeedBack")
    public int addFeedBack(@RequestBody FeedBack feedBack){
        return  feedBackService.addFeedBack(feedBack);
    }
    @PostMapping("/editFeedBack")
    public int editFeedBack(FeedBack feedBack){
        return feedBackService.editFeedBack(feedBack);
    }
    @GetMapping("/deleteFeedBack")
    public int deleteFeedBack(Integer id){
        return feedBackService.deleteFeedBack(id);
    }

    @GetMapping("/getData")
    public Result getData(){
        List<Integer> ints = feedBackService.getData();
        Collections.reverse(ints);
        List<String>  date = feedBackService.getDate();
        Collections.reverse(date);//翻转列表数据
        return Result.successAndObject(date,ints);
    }
}
