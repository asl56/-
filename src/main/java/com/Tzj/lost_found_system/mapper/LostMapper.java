package com.Tzj.lost_found_system.mapper;

import com.Tzj.lost_found_system.pojo.EchartsData;
import com.Tzj.lost_found_system.pojo.Lost;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LostMapper {
    @Select("select * from lost order by releaseDate desc ")
    List<Lost> getLostAll(Integer page, Integer count);

    List<Lost> getLost(@Param("page") Integer page, @Param("count") Integer count, @Param("id") Integer id, @Param("title") String title, @Param("description") String description, @Param("phone") String phone, @Param("userID") String userID, @Param("releaseDate") String releaseDate, @Param("statusID") Integer statusID);

    int editLost(Lost lost);

    @Insert("insert into lost(title, description, phone,ItemPhoto, userID) values(#{title}, #{description}, #{phone},#{ItemPhoto}, #{userID})")
    int addLost(Lost lost);

    @Delete("delete from lost where id=#{id}")
    int deleteLost(@Param("id") Integer id);
@Select("select distinct count(*) value,status.status name from lost left join status on lost.statusID=status.id group by statusID")
    List<EchartsData> getLostData();
}
