package com.Tzj.lost_found_system.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 公告表
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
    private Integer id;
    private String title; //公告标题
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date releaseDate;//发布时间
    private Integer userID;//发布人


    private String name;
}
