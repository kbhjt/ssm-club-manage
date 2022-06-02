package com.nchu.club.service;

import com.nchu.club.domain.CMessage;
import com.nchu.club.domain.Club;
import com.nchu.club.tablevo.ClubTableVo;
import com.nchu.club.vo.CMessageVo;

import java.util.List;

public interface ClubService {

    //获取所有的社团 分页查询
    ClubTableVo getAllClub(int page, int limit);

    //获取用户所在的社团
    List<Club> getClubByUid(int uid);

    //获取所有的社团信息
    List<Club> selectAllClubs();

    //通过社团id获取指定社团
    Club getClubByCid(int cid);

    //查询用户是否是该社团的助理
    Integer getIsClubHelper(int uid, int cid);

    //新增社团页留言
    int addCMessage(CMessage cMessage);

    List<CMessageVo> getCMessageByCid(int cid);

}
