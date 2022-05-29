package com.nchu.club.service.impl;

import com.nchu.club.dao.ActivityDao;
import com.nchu.club.domain.Activity;
import com.nchu.club.service.ActivityService;
import com.nchu.club.tablevo.ActivityTableVo;
import com.nchu.club.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public ActivityTableVo getActivityByCid(int cid, int page, int limit) {
        List<Activity> activityList = activityDao.selectActivityByCid(cid);
        if (page < 1) {
            page = 1;
        }
        int total = activityList.size();
        int max = total % limit == 0 ? total / limit : (total / limit + 1);
        max = Math.max(1,max); //严谨判断 确保max不小于1
        if(page > max){
            page = max;
        }
        int start = (page - 1) * limit;
        int length = limit;
        int end = start + length;
        if(end >= total) {
            end = total;
        }
        List<Activity> newList = new ArrayList<>();
        for (int i = start; i < end; i++) {
            newList.add(activityList.get(i));
        }
        ActivityTableVo activityTableVo = new ActivityTableVo();
        activityTableVo.setCount(total);
        activityTableVo.setData(newList);
        return activityTableVo;
    }

    @Override
    public List<Activity> getAllClubActivity(int page, int limit) {
        List<Activity> activityList = activityDao.selectAllActivity();
        if (page < 1) {
            page = 1;
        }
        int total = activityList.size();
        int max = total % limit == 0 ? total / limit : (total / limit + 1);
        max = Math.max(1,max); //严谨判断 确保max不小于1
        if(page > max){
            page = max;
        }
        int start = (page - 1) * limit;
        int length = limit;
        int end = start + length;
        if(end >= total) {
            end = total;
        }
        List<Activity> newList = new ArrayList<>();
        for (int i = start; i < end; i++) {
            newList.add(activityList.get(i));
        }
        return newList;
    }

    @Override
    public List<Activity> getAllClubActivity() {
        return activityDao.selectAllActivity();
    }

}
