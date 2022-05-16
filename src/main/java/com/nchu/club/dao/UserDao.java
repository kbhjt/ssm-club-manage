package com.nchu.club.dao;
import com.nchu.club.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {

    //查询指定的用户
    @Select("select DISTINCT u.uid,u.uname,u.uemail,u.uclass,u.unick,u.uimage,u.usign,u.usex,u.upassword,u.uno,r.roleId" +
            " from user u,role r,club_member c" +
            " where c.uid = u.uid and c.rid = r.roleId" +
            " and u.uno = #{uno} and u.upassword = #{upassword} and r.roleId = #{roleId}")
    User selectOne(@Param("uno") String uno, @Param("upassword") String upassword, @Param("roleId") int roleId);

    //添加一个用户
    @Options(useGeneratedKeys = true, keyProperty = "uid")
    @Insert("insert into user(uname,uemail,uclass,unick,uimage,usign,usex,upassword,uno)" +
            " values(#{uname},#{uemail},#{uclass},#{unick},#{uimage},#{usign}," +
            "#{usex},#{upassword},#{uno})")
    int addUser(User user);

    //修改用户信息
    @Update("update user set uname = #{uname},uemail = #{uemail},uclass = #{uclass}," +
            "unick = #{unick},uimage = #{uimage},usign = #{usign},usex = #{usex}," +
            "upassword = #{upassword},uno = #{uno}" +
            " where uid = #{uid}")
    int updateUser(User user);

    //通过id查找用户
    @Select("select DISTINCT u.uid,u.uname,u.uemail,u.uclass,u.unick,u.uimage,u.usign,u.usex,u.upassword,u.uno,r.roleId" +
            " from user u,role r,club_member c" +
            " where c.uid = u.uid and c.rid = r.roleId" +
            " and u.uid = #{uid}")
    User selectById(String uid);

    //查找社团成员
    @Select("select u.*" +
            " from user u,club_member c" +
            " where c.uid = u.uid" +
            " and cid = #{cid}")
    List<User> selectClubUser(Integer cid);
}
