package com.Tzj.lost_found_system.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 联系表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    private Integer id;
    private String content;//联系内容
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date contactTime; //联系时间
    private Integer lostID; //物品ID
    private Integer userID;//发布人ID
    private Integer foundID;//招领ID

    private Integer itemsUserID;
    private String title;//标题
    private String itemPhoto;//物品照片
    private String name;
    private String uname;
    private String phone;
    private String status;
}
