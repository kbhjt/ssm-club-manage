package com.nchu.club.service;

import com.nchu.club.domain.Activity;
import com.nchu.club.domain.CTalk;
import com.nchu.club.tablevo.ActivityTableVo;
import com.nchu.club.vo.*;

import java.util.List;

public interface ActivityService {

    //统计社团活动信息分布
    List<ActivityDataVo> getActivityData(int cid);

    //统计社团活动人数分布
    List<ActivityPeopleNumVo> getPeopleNum(int cid);

    //查询指定社团的活动
    ActivityTableVo getActivityByCid(int cid, int page, int limit);

    //分页查询所有社团的活动
    List<Activity> getAllClubActivity(int page, int limit);

    //查询所有社团活动
    List<Activity> getAllClubActivity();

    //查询用户参加的活动
    List<Activity> getActivityByUid(int uid);

    //新增活动页留言
    int addCTalk(CTalk cTalk);
    //查找活动留言
    List<CTalk> getCTalkByAid(int aid);
    //删除活动留言
    int deleteCtalk(int aid);
    //关闭和开启活动讨论
    int isOpenComment(int iscomment,int aid);
    //查找活动讨论状态
    int findIsOpen(int aid);




}
