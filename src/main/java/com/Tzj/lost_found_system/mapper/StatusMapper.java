package com.Tzj.lost_found_system.mapper;

import com.Tzj.lost_found_system.pojo.Status;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StatusMapper {
    @Select("select * from status")
    List<Status> getAll();

    List<Status> getStatus(int id, String status);
@Select("select * from status where id in (1,2)")
    List<Status> getLostStatus();
    @Select("select * from status where id in (3,4)")
    List<Status> getFoundStatus();
    @Select("select * from status where id in (5,6)")
    List<Status> getUserStatus();
}
