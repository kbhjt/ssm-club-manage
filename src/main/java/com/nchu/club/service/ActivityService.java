package com.nchu.club.service;

import com.nchu.club.vo.ActivityDataVo;
import com.nchu.club.vo.ActivityPeopleNumVo;

import java.util.List;

public interface ActivityService {

    //统计社团活动信息分布
    List<ActivityDataVo> getActivityData(int cid);

    //统计社团活动人数分布
    List<ActivityPeopleNumVo> getPeopleNum(int cid);

}
