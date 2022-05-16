package com.nchu.club.service;

import com.nchu.club.domain.User;
import java.util.List;

public interface UserService {

    //登录
    User login(String uid,String upassword,int roleId);

    //获取社团所有成员
    List<User> getClubUser(Integer cid);

    //注册
    int register(User user);
}
