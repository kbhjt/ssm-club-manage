package com.nchu.club.domain;

public class Activity {

    private String aid;         //社团活动id
    private String aname;       //社团活动名称
    private String aintroduce;  //社团活动介绍
    private String aimage;      //社团活动图片
    private String abegintime;  //社团活动开始时间
    private String aendtime;    //社团活动结束时间
    private int isOpen;         //社团活动是否对全体学生开放
    private int isDelete;       //是否删除（假删除）

    public Activity() {}

    public Activity(String aid, String aname, String aintroduce, String aimage, String abegintime, String aendtime, int isOpen, int isDelete) {
        this.aid = aid;
        this.aname = aname;
        this.aintroduce = aintroduce;
        this.aimage = aimage;
        this.abegintime = abegintime;
        this.aendtime = aendtime;
        this.isOpen = isOpen;
        this.isDelete = isDelete;
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

    public int getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(int isOpen) {
        this.isOpen = isOpen;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
