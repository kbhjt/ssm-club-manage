package com.nchu.club.service;

import com.nchu.club.domain.User;

import java.util.Date;
import java.util.List;

public interface UserService {

    //登录
    User login(String uid,String upassword,int roleId);

    //获取社团所有成员
    List<User> getClubUser(Integer cid);

    //注册
    int register(User user);

    //忘记密码
    int upadteUserByCode(User user);

    //根据邮箱查询用户
    User getUserByEmail(String uemail);

    //修改密码
    int updatePwd(User user);
}
