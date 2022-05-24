package com.nchu.club.vo;

public class ActivityDataVo {
    private int aid;
    private int uid;
    private String aname;
    private String usex;
    private String uclass;//年级

    public ActivityDataVo() {}

    public ActivityDataVo(int aid, int uid, String aname, String usex, String uclass) {
        this.aid = aid;
        this.uid = uid;
        this.aname = aname;
        this.usex = usex;
        this.uclass = uclass;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }

    public String getUclass() {
        return uclass;
    }

    public void setUclass(String uclass) {
        this.uclass = uclass;
    }
}
