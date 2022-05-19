package com.nchu.club.service.impl;

import com.nchu.club.dao.UserDao;
import com.nchu.club.domain.User;
import com.nchu.club.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(String uid,String upassword,int roleId) {
        return userDao.selectOne(uid, upassword, roleId);
    }

    @Override
    public List<User> getClubUser(Integer cid) {
        return userDao.selectClubUser(cid);
    }

    @Override
    public int register(User user) {
        return userDao.addUser(user);
    }

    @Override
    public int upadteUserByCode(User user) {
        return userDao.updateValidataCodeAndOutdate(user);
    }

    @Override
    public User getUserByEmail(String uemail) {
        return userDao.selectOneByEmail(uemail);
    }

    @Override
    public int updatePwd(User user) {
        return userDao.updatePassword(user);
    }

    @Override
    public int updatePersonalMessage(User user) {
        return userDao.updateUser(user);
    }
}
