package com.nchu.club.service.impl;

import com.nchu.club.dao.ClubDao;
import com.nchu.club.dao.UserDao;
import com.nchu.club.domain.Club;
import com.nchu.club.service.ClubService;
import com.nchu.club.vo.ClubVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    private ClubDao clubDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<ClubVo> getAllClub(int page,int limit) {
        List<ClubVo> clubVoList = new ArrayList<>();
        List<Club> clubList = clubDao.selectAllClubs();
        for (Club club : clubList) {
            String cleader = club.getCleader();
            String uname = "";
            if(cleader != null && !cleader.equals("")) {
                uname = userDao.selectById(cleader).getUname();
            }
            ClubVo clubVo = new ClubVo();
            clubVo.setCid(club.getCid());
            clubVo.setCname(club.getCname());
            clubVo.setCleader(uname);
            clubVo.setCreateTime(club.getCreateTime());
            clubVo.setUpdateTime(club.getUpdateTime());
            clubVoList.add(clubVo);

        }
        if (page < 1) {
            page = 1;
        }
        int total = clubVoList.size();
        int max = total % limit == 0 ? total / limit : (total / limit + 1);
        max = Math.max(1,max); //严谨判断 确保max不小于1
        if(page > max){
            page = max;
        }
        int start = (page - 1) * limit;
        System.out.println(start);
        int length = limit;
        System.out.println(length);
        List<ClubVo> newList = new ArrayList<>();
        if(length >= clubVoList.size()) {
            length = clubVoList.size();
        }
        System.out.println(length);
        for (int i = start; i < length ; i++) {
            newList.add(clubVoList.get(i));
        }
        return newList;
    }
}
