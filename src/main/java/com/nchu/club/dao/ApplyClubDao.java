package com.nchu.club.dao;

import com.nchu.club.domain.Apply;
import com.nchu.club.domain.Club;
import com.nchu.club.vo.ApplyClubVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ApplyClubDao {

    //插入一条社团申请
    @Insert("insert into apply_club(uid,cid,isOut,isAgree,isDelete) values(#{uid},#{cid},#{isOut},0,0)")
    int insertOne(@Param("uid") int uid,
                  @Param("cid") int cid,
                  @Param("isOut") String isOut);

    //获取所有的社团待审批的申请
    @Select("select a.id,u.uno,u.uid,u.uname,u.uemail,a.isOut" +
            " from apply_club a,user u" +
            " where a.uid = u.uid and a.cid = #{cid} and a.isAgree = 0 and a.isDelete = 0")
    List<ApplyClubVo> selectAllApply(int cid);

    //查询所有的社团的已审批的申请
    @Select("select a.id,u.uno,u.uname,u.uemail,a.isOut" +
            " from apply_club a,user u" +
            " where a.uid = u.uid and a.cid = #{cid} and a.isAgree = 1 and a.isDelete = 0")
    List<ApplyClubVo> selectDoneApply(int cid);

    //同意社团申请(同时需要将该用户的信息同步到club_member中)
    @Update("update apply_club set isAgree = 1 where id = #{id}")
    int updateApplyByAgree(int id);

    //拒绝社团申请(直接将这条申请删除)
    @Update("update apply_club set isDelete = 1 where id = #{id}")
    int updateApplyByNoAgree(int id);

    //查询用户还在审核中的社团
    @Select("select c.* from apply_club a,club c" +
            " where c.cid = a.cid" +
            " and a.uid = #{uid}" +
            " and isAgree = 0")
    List<Club> getClubByUid(int uid);

    //同意申请助理请求
    //将club_member表中用户的rid该为3
    @Update("update club_member set rid = #{rid} where uid = #{uid} and cid = #{cid}")
    int updateRid(@Param("uid") int uid,
                  @Param("cid") int cid,
                  @Param("rid") int rid);

    //查询社团中指定条件的申请
    @Select("select * from apply_club where uid = #{uid} and cid = #{cid} and isAgree = 0")
    Apply selectOne(@Param("uid") int uid,
                    @Param("cid") int cid);

}
