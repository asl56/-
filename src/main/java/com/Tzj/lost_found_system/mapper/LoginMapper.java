package com.Tzj.lost_found_system.mapper;

import com.Tzj.lost_found_system.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {
    @Select("select user.*,status.status from user left join status on user.statusID=status.id where userName=#{userName} and password=#{password}")
    User login(@Param("userName") String userName,@Param("password") String password);
}
