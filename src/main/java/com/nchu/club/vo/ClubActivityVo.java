package com.nchu.club.vo;

public class ClubActivityVo {

    private Integer cid;
    private String aid;
    private String cname;
    private String aname;
    private String aintroduce;
    private String aimage;

    public ClubActivityVo() {}

    public ClubActivityVo(Integer cid, String aid, String cname, String aname, String aintroduce, String aimage) {
        this.cid = cid;
        this.aid = aid;
        this.cname = cname;
        this.aname = aname;
        this.aintroduce = aintroduce;
        this.aimage = aimage;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
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
}
