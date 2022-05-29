package com.nchu.club.service;

import com.nchu.club.domain.Club;
import com.nchu.club.tablevo.ApplyClubTableVo;
import com.nchu.club.vo.ApplyClubVo;
import java.util.List;

public interface ApplyClubService {

    //学生申请（退出or加入）
    int addClubApply(int uid,int cid,String isOut);

    //社团管理员查询所有的待审批的申请
    ApplyClubTableVo getAllApplyClub(int cid,int page,int limit);

    //社团管理员查询所有的已审批的申请
    ApplyClubTableVo getAllDoneApplyClub(int cid,int page,int limit);

    //社团管理员同意申请
    int agreeApply(int id,int uid,int cid,String isOut);

    //社团管理员拒绝申请
    int disAgreeApply(int id);

    //查询用户还在审核中的社团
    List<Club> getExamineClub(int uid);
}
