package com.nchu.club.controller;

import com.nchu.club.domain.Activity;
import com.nchu.club.domain.Apply;
import com.nchu.club.service.ActivityService;
import com.nchu.club.service.ApplyActivityService;
import com.nchu.club.tablevo.ApplyActivityTableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/applyactivity")
public class ApplyActivityController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private ApplyActivityService applyActivityService;


    @RequestMapping("/getActivity")
    @ResponseBody
    public List<Activity> getActivityByUid(int uid) {
        return activityService.getActivityByUid(uid);
    }


    @RequestMapping("/findapplyactivity")
    @ResponseBody
    public ApplyActivityTableVo findAllApplyActivity(int cid){
        List<Apply> applyList =applyActivityService.findApply(cid);
        ApplyActivityTableVo vo = new ApplyActivityTableVo();
        vo.setData(applyList);
        return vo;
    }
    @RequestMapping("/agreeapplyactivity")
    @ResponseBody
    public String  agreeApply(int applyid){
        int result =  applyActivityService.agreeApply(applyid);
        if (result == 1){
            return "操作成功";
        }else{
            return "操作失败";
        }
    }
    @RequestMapping("/disagreeapplyactivity")
    @ResponseBody
    public String disagreeApply(int applyid){
        int result =  applyActivityService.disagreeApply(applyid);
        if (result == 1){
            return "操作成功";
        }else{
            return "操作失败";
        }
    }
}
