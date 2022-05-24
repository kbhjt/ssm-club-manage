package com.nchu.club.vo;

import java.util.List;

public class ActivityAllDataVo {
    private List<ActivityPeopleNumVo> activityPeopleNumVoList;
    private List<String> activityList;
    private List<Integer> manList;
    private List<Integer> womanList;

    public ActivityAllDataVo() {}

    public ActivityAllDataVo(List<ActivityPeopleNumVo> activityPeopleNumVoList, List<String> activityList, List<Integer> manList, List<Integer> womanList) {
        this.activityPeopleNumVoList = activityPeopleNumVoList;
        this.activityList = activityList;
        this.manList = manList;
        this.womanList = womanList;
    }

    public List<ActivityPeopleNumVo> getActivityPeopleNumVoList() {
        return activityPeopleNumVoList;
    }

    public void setActivityPeopleNumVoList(List<ActivityPeopleNumVo> activityPeopleNumVoList) {
        this.activityPeopleNumVoList = activityPeopleNumVoList;
    }

    public List<String> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<String> activityList) {
        this.activityList = activityList;
    }

    public List<Integer> getManList() {
        return manList;
    }

    public void setManList(List<Integer> manList) {
        this.manList = manList;
    }

    public List<Integer> getWomanList() {
        return womanList;
    }

    public void setWomanList(List<Integer> womanList) {
        this.womanList = womanList;
    }
}
