package com.nchu.club.vo;

public class ClubVo {
    private Integer cid;
    private String cname;
    private String cleader;
    private String createTime;
    private String updateTime;
    private String cimage;
    private String cintroduce;

    public ClubVo() {}

    public ClubVo(Integer cid, String cname, String cleader, String createTime, String updateTime, String cimage, String cintroduce) {
        this.cid = cid;
        this.cname = cname;
        this.cleader = cleader;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.cimage = cimage;
        this.cintroduce = cintroduce;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCleader() {
        return cleader;
    }

    public void setCleader(String cleader) {
        this.cleader = cleader;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCimage() {
        return cimage;
    }

    public void setCimage(String cimage) {
        this.cimage = cimage;
    }

    public String getCintroduce() {
        return cintroduce;
    }

    public void setCintroduce(String cintroduce) {
        this.cintroduce = cintroduce;
    }
}
