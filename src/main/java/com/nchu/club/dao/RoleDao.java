package com.nchu.club.dao;

import com.nchu.club.domain.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleDao {

    @Select("select * from role")
    List<Role> selectAll();

    //查询用户在指定社团的角色
    @Select("select * from role r,club_member c where c.rid = r.roleId" +
            " and c.uid = #{uid}" +
            " and c.cid = #{cid}")
    Role selectUserRole(@Param("uid") int uid,
                        @Param("cid") int cid);
}
