package com.nchu.club.controller;

import com.nchu.club.domain.Activity;
import com.nchu.club.domain.CTalk;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @RequestMapping("/addCTalk")
    @ResponseBody
    public int addCTalk(String aid,String uid,@RequestParam("cmessage") String ctalk) {
        CTalk cTalk  = new CTalk();
        cTalk.setAid(Integer.parseInt(aid));
        cTalk.setUid(Integer.parseInt(uid));
        cTalk.setCmessage(ctalk);

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式(年-月-日-时-分-秒)
        String mcreateTime = dateFormat.format(now);
        cTalk.setMcreateTime(mcreateTime);
        return activityService.addCTalk(cTalk);
    }


    @RequestMapping("/getCTalkByAid")
    @ResponseBody
    public List<CTalk> getCTalkByAid(String aid) {
        return activityService.getCTalkByAid(Integer.parseInt(aid));
    }

    //修改讨论状态
    @RequestMapping("/isOpenComment")
    @ResponseBody
    public String isOpenComment(@RequestParam("iscomment")int iscomment,@RequestParam("aid") int aid){
        activityService.isOpenComment(iscomment,aid);
        return "操作成功";
    }

    //查询讨论状态
    @RequestMapping("/findisopen")
    @ResponseBody
    public int findisopen(int aid,Model model){
        model.addAttribute("check",activityService.findIsOpen(aid));
        return activityService.findIsOpen(aid);
    }

    @RequestMapping("/delete")
    public ModelAndView getActivityDetail1(int aid) {
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
        modelAndView.setViewName("/club_manager/activity_administration");
        return  modelAndView;
    }

}
