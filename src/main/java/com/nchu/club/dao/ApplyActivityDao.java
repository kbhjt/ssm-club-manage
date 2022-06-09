package com.nchu.club.dao;

import com.nchu.club.domain.Apply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ApplyActivityDao {

    @Insert("insert into")
    int insertApply(int uid,int aid,String content);

    //查询所有为批准的申请
    @Select("SELECT activity_apply.applyid,user.uid,user.uname,activity.aname,activity_apply.data, activity_apply.agree FROM `activity_apply`,`user`,`activity`\n" +
            "WHERE activity_apply.agree= 0 AND activity_apply.uid = user.uid and activity_apply.aid = activity.aid and activity_apply.cid = #{cid} and activity_apply.delete = 0")
    List<Apply> getAllApplyActivity(int cid);
    //同意申请
    @Update("UPDATE activity_apply SET activity_apply.agree = 1 WHERE activity_apply.applyid = #{applyid}")
    int agreeApply(int applyid);

    //不同意申请
    @Update("UPDATE activity_apply SET activity_apply.delete = 1 WHERE activity_apply.applyid = #{applyid}")
    int  disagreeApply(int applyid);

    
}
