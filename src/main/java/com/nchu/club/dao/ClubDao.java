package com.nchu.club.dao;

import com.nchu.club.domain.CMessage;
import com.nchu.club.domain.CTag;
import com.nchu.club.domain.Club;
import com.nchu.club.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

public interface ClubDao {

    //删除一个社团(假删除将delete置为1）
    @Update("update club set delete = 1 where cid = #{cid}")
    int deleteClub(int cid);

    //添加一个社团
    @Insert("insert into club values(null,#{cname},#{cintroduce}," +
            "#{cleader},#{chelper},0)")
    int addClub(Club club);

    //修改社团信息
    @Update("update club set cname = #{cname},cintroduce = #{cintroduce}," +
            "cleader = #{cleader},chelper = #{chelper}")
    int updateClub(@Param("cid") int cid,Club club);

    //查询社团
    @Select("select * from club where isDelete = ${0}")
    List<Club> selectAllClubs();

    //查询指定社团
    @Select("select * from club where cid = #{cid} and isDelete = 0 ")
    Club selectClubById(int cid);

    //增加或修改社团负责人
    @Update("update club set cleader = #{uid} where cid = #{cid}")
    int updateClubLeader(@Param("cid") int cid,
                      @Param("uid") int uid);
    //添加或删除社团助理（删除将该值置为0）
    @Update("update club set chelper = #{uid} where cid = #{cid}")
    int updateClubHelper(@Param("cid") int cid,
                         @Param("uid") int uid);
    //同时将信息更新到club_member表中
    @Update("update club_member set uid = #{uid},cid = #{cid},rid = #{rid},delete = 0")
    int updateClubMember(@Param("uid") int uid,
                         @Param("cid") int cid,
                         @Param("rid") int rid);

    //通过用户id查询所在的社团
    @Select("select c.* from club c,club_member c1" +
            " where c.cid = c1.cid" +
            " and c1.uid = #{uid}")
    List<Club> selectClubByUid(int uid);

    //查询用户是否为该社团的助理
    @Select("select cid from club_member where uid = #{uid} and cid = #{cid} and rid = 3")
    Integer selectClubHelper(@Param("uid") int uid,
                             @Param("cid") int cid);

    @Insert("insert into club_message(cid,uid,cmessage,mcreateTime,isDelete) values(#{cid},#{uid}," +
            "#{cmessage},#{mcreateTime},0)")
    int addCMessage(CMessage cMessage);

    //根据cid查询所有的留言内容
    @Select("select * from club_message where cid = #{cid} and isDelete = 0")
    List<CMessage> selectCMeassgeByCid(int cid);

    @Insert("insert into club_tag(cid,ctag) values(#{cid},#{ctag})")
    int addCTage(CTag cTag);

    @Select("select ctag from club_tag where cid = #{cid}")
    List<String> getCTagByCid(int cid);

    //删除一个留言(假删除将delete置为1）
    @Update("update club_message set isDelete = 1 where mid = #{mid}")
    int deleteCMeassge(int mid);

}
