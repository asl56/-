package com.Tzj.lost_found_system.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 建议表
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class FeedBack {

    private Integer id;
    private String content;//反馈内容
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date released;//发布时间
    private Integer userID;//发布人ID

    private String name;
    private String avatar;
}
