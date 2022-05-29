package com.nchu.club.service;

import com.nchu.club.domain.Role;
import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findUserRole(int uid,int cid);
}
