package com.nchu.club.controller;

import com.nchu.club.service.ApplyClubService;
import com.nchu.club.tablevo.ApplyClubTableVo;
import com.nchu.club.vo.ApplyClubVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/apply")
public class ApplyClubController {

    @Autowired
    private ApplyClubService applyClubService;

    @RequestMapping("/getAllApplyClub")
    @ResponseBody
    public ApplyClubTableVo getAllApplyClub(int cid,int page,int limit) {
        return applyClubService.getAllApplyClub(cid,page,limit);
    }

    @RequestMapping("/getAllDoneApplyClub")
    @ResponseBody
    public ApplyClubTableVo getAllDoneApplyClub(int cid,int page,int limit) {
        return applyClubService.getAllDoneApplyClub(cid, page, limit);
    }

    @RequestMapping("/applyClub")
    @ResponseBody
    public String applyClub(int uid,int cid,String isOut) {
        if(applyClubService.addClubApply(uid, cid, isOut) > 0) {
            return "申请成功，正在审核";
        }
        return "服务器异常，申请失败";
    }

    @RequestMapping("/agreeClubApply")
    @ResponseBody
    public String agreeClubApply(int id,int uid,int cid,String isOut) {
        if(applyClubService.agreeApply(id, uid, cid, isOut) > 0) {
            return "操作成功";
        }
        return "服务器异常，操作失败";
    }

}
