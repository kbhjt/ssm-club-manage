package com.nchu.club.service.impl;

import com.nchu.club.dao.ApplyClubDao;
import com.nchu.club.dao.RoleDao;
import com.nchu.club.dao.UserDao;
import com.nchu.club.domain.Role;
import com.nchu.club.domain.User;
import com.nchu.club.service.UserService;
import com.nchu.club.vo.ClubUserTableVo;
import com.nchu.club.vo.ClubUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ApplyClubDao applyClubDao;

    @Override
    public User login(String uid,String upassword,int roleId) {
        return userDao.selectOne(uid, upassword, roleId);
    }

    //查询社团所有用户
    @Override
    public List<ClubUserVo> getClubUser(Integer cid) {
        List<User> userList = userDao.selectClubUser(cid);
        List<ClubUserVo> clubUserVoList = new ArrayList<>();
        for (User user : userList) {
            Role role = roleDao.selectUserRole(user.getUid(),cid);
            ClubUserVo clubUserVo = new ClubUserVo();
            clubUserVo.setUid(user.getUid());
            clubUserVo.setUno(user.getUno());
            clubUserVo.setUname(user.getUname());
            clubUserVo.setRoleName(role.getRoleName());
            clubUserVo.setUemail(user.getUemail());
            clubUserVoList.add(clubUserVo);
        }
        return clubUserVoList;
    }
    //分页查询
    @Override
    public ClubUserTableVo getTableUsers(int cid, int page, int limit) {
       List<ClubUserVo> clubUserVoList = this.getClubUser(cid);
        if (page < 1) {
            page = 1;
        }
        int total = clubUserVoList.size();
        int max = total % limit == 0 ? total / limit : (total / limit + 1);
        max = Math.max(1,max); //严谨判断 确保max不小于1
        if(page > max){
            page = max;
        }
        int start = (page - 1) * limit;
        int length = limit;
        int end = start + length;
        if(end >= total) {
            end = total;
        }
        List<ClubUserVo> newList = new ArrayList<>();
        for (int i = start; i < end; i++) {
            newList.add(clubUserVoList.get(i));
        }
        ClubUserTableVo clubUserTableVo = new ClubUserTableVo();
        clubUserTableVo.setCount(total);
        clubUserTableVo.setData(newList);
        return clubUserTableVo;
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

    @Override
    public User getUserByUid(int uid) {
        return userDao.getUserByUid(uid);
    }

    @Override
    public int updateUserRole(int uid, int cid, int rid) {
        return applyClubDao.updateRid(uid,cid,rid);
    }


}
