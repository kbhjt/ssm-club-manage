package com.nchu.club.dao;

import com.nchu.club.domain.Activity;
import com.nchu.club.vo.ActivityUserVo;
import com.nchu.club.vo.ClubActivityVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

public interface ActivityDao {

    //添加一个社团活动
    @Insert("insert into activity values(null,#{aname}," +
            "#{aintroduce},#{aimage},#{atime},0")
    int addActivity(Activity activity);

    //删除一个社团活动（将delete置为1）
    @Update("update activity set delete = 1 where aid = #{aid}")
    int deleteActivity(String aid);
    //并同时将club_activity中的delete置为1
    @Update("update club_activity set delete  = 1 where cid = #{cid} and aid = #{aid}")
    int updateClubActivity(@Param("cid") String cid,
                           @Param("aid") String aid);

    //修改社团活动信息
    @Update("update activitu set aname = #{aname},aintroduce = #{aintroduce}," +
            "aimage = #{aimage},atime = #{atime},delete = #{delete}" +
            " where aid = #{aid}")
    int updateActivity(Activity activity);

    //查询所有社团活动信息
    @Select("select c1.cid,c1.aid,cname,aname,aintroduce,aimage,atime" +
            " from activity a,club_activity c1,club c2" +
            " where a.aid = c1.aid and c1.aid = c2.aid")
    List<ClubActivityVo> selectAllActivity();

    //查询指定社团活动信息
    @Select("select c1.cid,c1.aid,cname,aname,aintroduce,aimage,atime" +
            " from activity a,club_activity c1,club c2" +
            " where a.aid = c1.aid and c1.aid = c2.aid" +
            " and c1.cid = #{cid}")
    List<ClubActivityVo> selectActivityById(String cid);

    //查询社团活动的统计信息（人数、性别、年级）
    @Select("select c1.cid,c1.aid,c2.uid,u.usex,u.uclass" +
            " from activity_user a,club_activity c1,club_member c2,user u" +
            " where a.aid = c1.aid and a.uid = c2.uid" +
            " and c1.cid = c2.cid and u.uid = a.uid" +
            " and c1.cid = #{cid}")
    List<ActivityUserVo> selectActivityUserById(String cid);


}
