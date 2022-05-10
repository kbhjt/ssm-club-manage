package com.nchu.club.dao;
import com.nchu.club.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {

    @Select("select DISTINCT u.uid,u.uname,u.uemail,u.uclass,u.unick,u.uimage,u.usign,u.usex,u.upassword,r.roleId" +
            " from user u,role r,club_member c" +
            " where c.uid = u.uid and c.rid = r.roleId" +
            " and u.uid = #{uid} and u.upassword = #{upassword} and r.roleId = #{roleId}")
    User selectOne(@Param("uid") String uid, @Param("upassword") String upassword, @Param("roleId") int roleId);
}
