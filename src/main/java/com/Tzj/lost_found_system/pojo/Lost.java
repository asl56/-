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
public class Lost {
    private Integer id;
    private String title;//标题
    private String description;//描述
    private String phone;//联系方式
    private Integer userID;//发布人ID
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date releaseDate;//发布日期
    private String ItemPhoto;//物品照片
    private Integer statusID;//物品状态

    private String name;
    private String status;
}
