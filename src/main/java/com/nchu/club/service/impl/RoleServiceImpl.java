package com.nchu.club.service.impl;

import com.nchu.club.dao.RoleDao;
import com.nchu.club.domain.Role;
import com.nchu.club.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.selectAll();
    }

    @Override
    public Role findUserRole(int uid,int cid) {
        return roleDao.selectUserRole(uid,cid);
    }
}
