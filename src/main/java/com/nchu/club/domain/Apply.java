package com.nchu.club.domain;

public class Apply {
    private int applyid;
    private int uid;
    private String uname;
    private String aname;
    private String data;

    public Apply(int applyid, int uid, String uname, String aname, String data) {
        this.applyid = applyid;
        this.uid = uid;
        this.uname = uname;
        this.aname = aname;
        this.data = data;
    }

    public int getApplyid() {
        return applyid;
    }

    public void setApplyid(int applyid) {
        this.applyid = applyid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}