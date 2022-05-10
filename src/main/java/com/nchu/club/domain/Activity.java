package com.nchu.club.domain;

public class Activity {

    private String aid;         //社团活动id
    private String aname;       //社团活动名称
    private String aintroduce;  //社团活动介绍
    private String aimage;      //社团活动图片
    private String atime;       //社团活动时间
    private int delete;         //是否删除（假删除）

    public Activity() {}

    public Activity(String aid, String aname, String aintroduce, String aimage, String atime, int delete) {
        this.aid = aid;
        this.aname = aname;
        this.aintroduce = aintroduce;
        this.aimage = aimage;
        this.atime = atime;
        this.delete = delete;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
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

    public String getAtime() {
        return atime;
    }

    public void setAtime(String atime) {
        this.atime = atime;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }
}
