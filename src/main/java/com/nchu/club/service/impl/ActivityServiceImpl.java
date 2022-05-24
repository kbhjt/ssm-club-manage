package com.nchu.club.service.impl;

import com.nchu.club.dao.ActivityDao;
import com.nchu.club.service.ActivityService;
import com.nchu.club.vo.ActivityDataVo;
import com.nchu.club.vo.ActivityPeopleNumVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;

    @Override
    public List<ActivityDataVo> getActivityData(int cid) {
        return activityDao.selectActivityUserById(cid);
    }

    @Override
    public List<ActivityPeopleNumVo> getPeopleNum(int cid) {
        return activityDao.selectActivityPeopleNum(cid);
    }
}
