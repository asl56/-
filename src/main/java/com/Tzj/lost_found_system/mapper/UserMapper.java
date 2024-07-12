package com.Tzj.lost_found_system.mapper;

import com.Tzj.lost_found_system.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user left join status on user.statusID=status.id")
    List<User> getAll(Integer page,Integer count);

    List<User> getUser(@Param("id") Integer id, @Param("userName") String userName, @Param("password") String password, @Param("name") String name, @Param("role") String role, @Param("phone") String phone, @Param("email") String email,@Param("page")Integer page,@Param("count")Integer count,@Param("statusID") Integer statusID);

    int editUser(User user);

    @Insert("insert into user(userName,password,name,avatar,role,phone,email) values (#{userName},#{password},#{name},#{avatar},#{role},#{phone},#{email}) ")
    int addUser(User user);

    @Delete("delete from user where id=#{id}")
    int deleteUser(@Param("id") Integer id);
}
