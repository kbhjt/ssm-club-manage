package com.nchu.club.service;

import com.nchu.club.domain.User;
import com.nchu.club.vo.ClubUserTableVo;
import com.nchu.club.vo.ClubUserVo;

import java.util.Date;
import java.util.List;

public interface UserService {

    //登录
    User login(String uid,String upassword,int roleId);

    //查询获取社团所有成员
    List<ClubUserVo> getClubUser(Integer cid);
    //分页查询
    ClubUserTableVo getTableUsers(int cid,int page,int limit);

    //注册
    int register(User user);

    //忘记密码
    int upadteUserByCode(User user);

    //根据邮箱查询用户
    User getUserByEmail(String uemail);

    //修改密码
    int updatePwd(User user);

    //设置个人信息
    int updatePersonalMessage(User user);

    //查询指定用户
    User getUserByUid(int uid);
}
