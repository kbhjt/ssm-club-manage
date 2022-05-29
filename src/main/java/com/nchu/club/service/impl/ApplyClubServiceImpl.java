package com.nchu.club.service.impl;

import com.nchu.club.dao.ApplyClubDao;
import com.nchu.club.dao.UserDao;
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
        if(applyClubDao.updateApplyByAgree(id) > 0) {
            if(isOut.equals("加入")) { //申请加入社团
                if (userDao.updateClubMemberAgree(uid,cid) > 0) {
                    System.out.println("更新1？");
                    return 1;
                }
            }else { //申请退出社团
                if (userDao.updateClubMemberOut(uid,cid) > 0) {
                    System.out.println("更新2？");
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
}
