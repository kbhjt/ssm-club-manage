package com.nchu.club.controller;

import com.nchu.club.domain.Activity;
import com.nchu.club.domain.Club;
import com.nchu.club.service.ActivityService;
import com.nchu.club.service.ClubService;
import com.nchu.club.service.UserService;
import com.nchu.club.tablevo.ActivityTableVo;
import com.nchu.club.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private ClubService clubService;
    @Autowired
    private UserService userService;

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

    @RequestMapping("/getActivityByCid")
    @ResponseBody
    public ActivityTableVo getActivityByCid(int cid, int page, int limit) {
        return activityService.getActivityByCid(cid, page, limit);
    }

    @RequestMapping("/getAllClubActivity")
    @ResponseBody
    public List<Activity> getAllClubActivity(int page,int limit,Model model) {
        model.addAttribute("total",activityService.getAllClubActivity().size());
        return activityService.getAllClubActivity(page, limit);
    }

    @RequestMapping("/detail")
    public ModelAndView getActivityDetail(int aid) {
        List<Activity> activityList = activityService.getAllClubActivity();
        ClubActivityVo clubActivityVo = new ClubActivityVo();
        String uname = "";
        for (Activity activity : activityList) {
            if(activity.getAid() == aid) {
                clubActivityVo.setActivity(activity);
                Club club = clubService.getClubByCid(activity.getCid());
                clubActivityVo.setClub(club);
                uname = userService.getUserByUid(club.getCleader()).getUname();
                break;
            }
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("activity",clubActivityVo);
        modelAndView.addObject("uname",uname);
        modelAndView.setViewName("/student/activity_detail");
        return  modelAndView;
    }
}
