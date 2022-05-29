package com.nchu.club.domain;

public class Activity {

    private int aid;         //社团活动id
    private String aname;       //社团活动名称
    private String aintroduce;  //社团活动介绍
    private String aimage;      //社团活动图片
    private String abegintime;  //社团活动开始时间
    private String aendtime;    //社团活动结束时间
    private int cid;
    private int isOpen;         //社团活动是否对全体学生开放
    private int peopleLimit;    //人数限制
    private String activityType;   //活动类型
    private String activityPosition;   //活动地点
    private int isDelete;       //是否删除（假删除）

    public Activity() {}

    public Activity(int aid, String aname, String aintroduce, String aimage, String abegintime, String aendtime, int cid, int isOpen, int peopleLimit, String activityType, String activityPosition, int isDelete) {
        this.aid = aid;
        this.aname = aname;
        this.aintroduce = aintroduce;
        this.aimage = aimage;
        this.abegintime = abegintime;
        this.aendtime = aendtime;
        this.cid = cid;
        this.isOpen = isOpen;
        this.peopleLimit = peopleLimit;
        this.activityType = activityType;
        this.activityPosition = activityPosition;
        this.isDelete = isDelete;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getAintroduce() {
        return aintroduce;
    }

    public void setAintroduce(String aintroduce) {
        this.aintroduce = aintroduce;
    }

    public String getAimage() {
        return aimage;
    }

    public void setAimage(String aimage) {
        this.aimage = aimage;
    }

    public String getAbegintime() {
        return abegintime;
    }

    public void setAbegintime(String abegintime) {
        this.abegintime = abegintime;
    }

    public String getAendtime() {
        return aendtime;
    }

    public void setAendtime(String aendtime) {
        this.aendtime = aendtime;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(int isOpen) {
        this.isOpen = isOpen;
    }

    public int getPeopleLimit() {
        return peopleLimit;
    }

    public void setPeopleLimit(int peopleLimit) {
        this.peopleLimit = peopleLimit;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityPosition() {
        return activityPosition;
    }

    public void setActivityPosition(String activityPosition) {
        this.activityPosition = activityPosition;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
