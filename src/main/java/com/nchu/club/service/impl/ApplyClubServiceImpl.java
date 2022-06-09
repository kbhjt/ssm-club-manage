package com.nchu.club.service.impl;

import com.nchu.club.dao.ApplyClubDao;
import com.nchu.club.dao.ClubDao;
import com.nchu.club.dao.UserDao;
import com.nchu.club.domain.ApplyClub;
import com.nchu.club.domain.Club;
import com.nchu.club.service.ApplyClubService;
import com.nchu.club.tablevo.ApplyClubTableVo;
import com.nchu.club.vo.ApplyClubVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplyClubServiceImpl implements ApplyClubService {

    @Autowired
    private ApplyClubDao applyClubDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ClubDao clubDao;

    @Override
    public int addClubApply(int uid, int cid, String isOut) {
        return applyClubDao.insertOne(uid, cid, isOut);
    }

    @Override
    public ApplyClubTableVo getAllApplyClub(int cid,int page,int limit) {
        List<ApplyClubVo> applyClubVoList = applyClubDao.selectAllApply(cid);
        if (page < 1) {
            page = 1;
        }
        int total = applyClubVoList.size();
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
        List<ApplyClubVo> newList = new ArrayList<>();
        for (int i = start; i < end; i++) {
            newList.add(applyClubVoList.get(i));
        }
        ApplyClubTableVo applyClubTableVo = new ApplyClubTableVo();
        applyClubTableVo.setCount(total);
        applyClubTableVo.setData(newList);
        return applyClubTableVo;
    }

    @Override
    public ApplyClubTableVo getAllDoneApplyClub(int cid,int page,int limit) {
        List<ApplyClubVo> applyClubVoList = applyClubDao.selectDoneApply(cid);
        if (page < 1) {
            page = 1;
        }
        int total = applyClubVoList.size();
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
        List<ApplyClubVo> newList = new ArrayList<>();
        for (int i = start; i < end; i++) {
            newList.add(applyClubVoList.get(i));
        }
        ApplyClubTableVo applyClubTableVo = new ApplyClubTableVo();
        applyClubTableVo.setCount(total);
        applyClubTableVo.setData(newList);
        return applyClubTableVo;
    }

    @Override
    public int agreeApply(int id,int uid,int cid,String isOut) {
        List<Club> clubList = clubDao.selectClubByUid(uid);
        System.out.println(clubList.size());
        if(applyClubDao.updateApplyByAgree(id) > 0) {
            if(isOut.equals("加入社团")) { //申请加入社团
                if(clubList != null && clubList.size() != 0) { //向club_member表中新增一条数据
                    if(userDao.insertClubMember(uid,cid,4) > 0) {
                        System.out.println("插入1");
                        return 1;
                    }
                }else {//如果为空 说明数据库中用户所对应的cid=0 需要更新
                    if (userDao.updateClubMemberAgree(uid,cid) > 0) {
                        System.out.println("更新1？");
                        return 1;
                    }
                }
            }else if(isOut.equals("申请退出")){ //申请退出社团
                if(clubList.size() == 1) { //将cid更新为0
                    if (userDao.updateClubMemberOut(uid,cid) > 0) {
                        System.out.println("退出1？");
                        return 1;
                    }
                }else if(clubList.size() > 1){ //大于1 在club_member表中删除该条记录
                    if(userDao.deleteClubMemer(uid,cid) > 0) {
                        System.out.println("退出2");
                        return 1;
                    }
                }
            }else { //申请助理
                if(applyClubDao.updateRid(uid,cid,3) > 0) {
                    return 1;
                }
            }
        }
        System.out.println("???");
        return 0;
    }

    @Override
    public int disAgreeApply(int id) {
        return applyClubDao.updateApplyByNoAgree(id);
    }

    @Override
    public List<Club> getExamineClub(int uid) {
        return applyClubDao.getClubByUid(uid);
    }

    @Override
    public ApplyClub getOneApply(int uid, int cid) {
        return applyClubDao.selectOne(uid,cid);
    }


}
