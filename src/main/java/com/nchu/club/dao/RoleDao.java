package com.nchu.club.dao;

import com.nchu.club.domain.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleDao {

    @Select("select * from role")
    List<Role> selectAll();
}
