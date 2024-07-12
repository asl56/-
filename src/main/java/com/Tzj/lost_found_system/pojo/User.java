package com.Tzj.lost_found_system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 用户表
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String userName;//用户名
    private String password;//密码
    private String name;//姓名
    private String avatar;//头像
    private String role;//角色标识
    private String phone;//电话
    private String email;//邮箱
    private Integer statusID;//状态

    private String status;

}
