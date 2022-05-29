package com.nchu.club.dao;

import com.nchu.club.domain.Activity;
import com.nchu.club.vo.ActivityDataVo;
import com.nchu.club.vo.ActivityPeopleNumVo;
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
    @Select("select * from activity where isDelete = 0")
    List<Activity> selectAllActivity();

    //查询指定社团活动的信息
    @Select("select * from activity where isDelete = 0 and cid = #{cid}")
    List<Activity> selectActivityByCid(int cid);

//    //查询指定社团活动信息
//    @Select("select c1.cid,c1.aid,cname,aname,aintroduce,aimage,atime" +
//            " from activity a,club_activity c1,club c2" +
//            " where a.aid = c1.aid and c1.aid = c2.aid" +
//            " and c1.cid = #{cid}")
//    List<ClubActivityVo> selectActivityById(String cid);

    //查询社团活动的统计信息（人数、性别、年级）
    @Select("select u1.uid,u1.usex,u1.uclass,a1.aid,a1.aname" +
            " from user u1,club_member c1,activity a1,activity_user a2,club c2" +
            " where u1.uid = c1.uid and u1.uid = a2.uid and a1.cid = c2.cid" +
            " and u1.uid = a2.uid and a1.aid = a2.aid and c2.cid = #{cid}")
    List<ActivityDataVo> selectActivityUserById(int cid);

    //查询社团活动人数分布
    @Select("select count(a1.uid) as value,a2.aname as name from activity_user a1,activity a2 where a1.aid = a2.aid and a2.cid = #{cid} GROUP BY a1.aid")
    List<ActivityPeopleNumVo> selectActivityPeopleNum(int cid);


}
