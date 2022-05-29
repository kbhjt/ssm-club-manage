package com.nchu.club.service;

import com.nchu.club.domain.Activity;
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

}
