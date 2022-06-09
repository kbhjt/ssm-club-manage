package com.nchu.club.service.impl;

import com.nchu.club.dao.ClubDao;
import com.nchu.club.dao.UserDao;
import com.nchu.club.domain.*;
import com.nchu.club.service.ClubService;
import com.nchu.club.tablevo.ActivityTableVo;
import com.nchu.club.tablevo.CMessageTableVo;
import com.nchu.club.tablevo.ClubTableVo;
import com.nchu.club.vo.CMessageVo;
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
    public ClubTableVo getAllClub(int page, int limit) {
        List<ClubVo> clubVoList = new ArrayList<>();
        List<Club> clubList = clubDao.selectAllClubs();
        ClubTableVo clubTableVo = new ClubTableVo();
        for (Club club : clubList) {
            Integer cleader = club.getCleader();
            String uname = "";
            if(cleader != null) {
                uname = userDao.selectById(cleader).getUname();
            }
            ClubVo clubVo = new ClubVo();
            clubVo.setCid(club.getCid());
            clubVo.setCname(club.getCname());
            clubVo.setCleader(uname);
            clubVo.setCreateTime(club.getCreateTime());
            clubVo.setUpdateTime(club.getUpdateTime());
            clubVo.setCimage(club.getCimage());
            clubVo.setCintroduce(club.getCintroduce());
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
        int end = start + length;
        List<ClubVo> newList = new ArrayList<>();
        if(end >= clubVoList.size()) {
            end = clubVoList.size();
        }
        for (int i = start; i < end ; i++) {
            newList.add(clubVoList.get(i));
        }
        clubTableVo.setCount(total);
        clubTableVo.setData(newList);
        return clubTableVo;
    }

    @Override
    public List<Club> getClubByUid(int uid) {
        return clubDao.selectClubByUid(uid);
    }

    @Override
    public List<Club> selectAllClubs() {
        return clubDao.selectAllClubs();
    }

    @Override
    public Club getClubByCid(int cid) {
        return clubDao.selectClubById(cid);
    }

    @Override
    public Integer getIsClubHelper(int uid, int cid) {
        return clubDao.selectClubHelper(uid, cid);
    }

    @Override
    public int addCMessage(CMessage cMessage) {
        return clubDao.addCMessage(cMessage);
    }

    @Override
    public List<CMessageVo> getCMessageByCid(int cid) {
        List<CMessage> cMessageList = clubDao.selectCMeassgeByCid(cid);
        List<CMessageVo> voList = new ArrayList<>();
        for (CMessage cMessage : cMessageList) {
            User user = userDao.getUserByUid(cMessage.getUid());
            CMessageVo vo = new CMessageVo();
            vo.setUid(cMessage.getUid());
            vo.setCid(cMessage.getCid());
            vo.setMid(cMessage.getMid());
            vo.setCmessage(cMessage.getCmessage());
            vo.setMcreateTime(cMessage.getMcreateTime());
            vo.setUname(user.getUname());
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public int addCTag(CTag cTag) {
        return clubDao.addCTage(cTag);
    }

    @Override
    public List<String> getCTagByCid(int cid) {
        return clubDao.getCTagByCid(cid);
    }

    @Override
    public CMessageTableVo getMessageVo(int page, int limit, int cid) {
        List<CMessageVo> messageVoList = this.getCMessageByCid(cid);
        if (page < 1) {
            page = 1;
        }
        int total = messageVoList.size();
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
        List<CMessageVo> newList = new ArrayList<>();
        for (int i = start; i < end; i++) {
            newList.add(messageVoList.get(i));
        }
        CMessageTableVo cMessageTableVo = new CMessageTableVo();
        cMessageTableVo.setCount(total);
        cMessageTableVo.setData(newList);
        return cMessageTableVo;
    }

    @Override
    public int deleteCMeassge(int mid) {
        return clubDao.deleteCMeassge(mid);
    }


}
