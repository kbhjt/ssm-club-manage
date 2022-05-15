package com.nchu.club.service;

import com.nchu.club.domain.User;
import java.util.List;

public interface UserService {

    User login(String uid,String upassword,int roleId);

    List<User> getClubUser(String cid);
}
