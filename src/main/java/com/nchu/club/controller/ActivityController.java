package com.nchu.club.controller;

import com.nchu.club.service.ActivityService;
import com.nchu.club.vo.ActivityAllDataVo;
import com.nchu.club.vo.ActivityDataVo;
import com.nchu.club.vo.ActivityPeopleNumVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping("/getActivityData")
    @ResponseBody
    public ActivityAllDataVo getActivityData(int cid, Model model) {
        List<ActivityPeopleNumVo> list = activityService.getPeopleNum(cid);
        List<ActivityDataVo> activityDataVoList = activityService.getActivityData(cid);
        //定义两个计数器
        int man = 0;
        int woman = 0;
        //定义两个list集合存储性别数据信息
        List<Integer> manList = new ArrayList<>();
        List<Integer> womanList = new ArrayList<>();
        for (ActivityPeopleNumVo activityPeopleNumVo : list) {
            for (ActivityDataVo activityDataVo : activityDataVoList) {
                if(activityDataVo.getAname().equals(activityPeopleNumVo.getName())) {
                    if(activityDataVo.getUsex().equals("男")) {
                        man++;
                    }else {
                        woman++;
                    }
                }
            }
            manList.add(man);
            womanList.add(woman);
            man = 0;
            woman = 0;
        }
        //定义一个List集合存储活动数据信息
        List<String> activityList = new ArrayList<>();
        for (ActivityPeopleNumVo activityPeopleNumVo : list) {
            activityList.add(activityPeopleNumVo.getName());
        }
        ActivityAllDataVo activityAllDataVo = new ActivityAllDataVo(list,activityList,manList,womanList);
        return activityAllDataVo;
    }
}
