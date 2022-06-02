package com.nchu.club.vo;

public class CMessageVo {
    private Integer mid;//留言id
    private Integer cid;//社团id
    private Integer uid;//留言学生id
    private String cmessage;//留言内容
    private String mcreateTime;//留言时间
    private String uname; //留言学生

    public CMessageVo() {}

    public CMessageVo(Integer mid, Integer cid, Integer uid, String cmessage, String mcreateTime, String uname) {
        this.mid = mid;
        this.cid = cid;
        this.uid = uid;
        this.cmessage = cmessage;
        this.mcreateTime = mcreateTime;
        this.uname = uname;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
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

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
