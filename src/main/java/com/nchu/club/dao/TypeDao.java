package com.nchu.club.dao;

import com.nchu.club.domain.Club;
import com.nchu.club.domain.Type;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TypeDao {
    //删除一个活动类型(假删除将delete置为1）
    @Update("UPDATE activity_type SET activity_type.`delete` = 1 WHERE activity_type.atid = #{atid}")
    int deleteType(int atid);

    //添加一个活动类型
    @Insert("INSERT INTO activity_type VALUES(NULL,#{type},#{number},#{time},0)")
    int addType(Type type);

    //修改活动类型
    @Update("update activity_type set type = #{type},number = #{number}," +
            "time = #{time} WHERE atid = #{atid}")
    int updateType(@Param("atid") int atid, Type type);

    //查询活动类型
    @Select("select * from activity_type where activity_type.delete = ${0}")
    List<Type> selectAllType();

    @Select("SELECT * FROM activity_type WHERE activity_type.atid = #{atid}")
    Type selectTypeById(int atid);


}