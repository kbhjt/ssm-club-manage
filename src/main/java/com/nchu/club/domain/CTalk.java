package com.nchu.club.domain;

public class CTalk {
    private Integer tid;//留言id
    private Integer aid;//社团id
    private Integer uid;//留言学生id
    private String cmessage;//留言内容
    private String mcreateTime;//留言时间
    private int isDelete;//是否删除

    public CTalk(){};
    public CTalk(Integer tid, Integer aid, Integer uid, String cmessage, String mcreateTime, int isDelete) {
        this.tid = tid;
        this.aid = aid;
        this.uid = uid;
        this.cmessage = cmessage;
        this.mcreateTime = mcreateTime;
        this.isDelete = isDelete;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getCmessage() {
        return cmessage;
    }

    public void setCmessage(String cmessage) {
        this.cmessage = cmessage;
    }

    public String getMcreateTime() {
        return mcreateTime;
    }

    public void setMcreateTime(String mcreateTime) {
        this.mcreateTime = mcreateTime;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}