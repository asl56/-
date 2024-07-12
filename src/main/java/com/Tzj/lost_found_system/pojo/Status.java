package com.Tzj.lost_found_system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 状态表
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status {
    private Integer id;
    private String status; //物品状态
}
