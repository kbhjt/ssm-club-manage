package com.nchu.club.vo;

public class ClubVo {
    private Integer cid;
    private String cname;
    private String cleader;
    private String createTime;
    private String updateTime;

    public ClubVo() {}

    public ClubVo(Integer cid, String cname, String cleader, String createTime, String updateTime) {
        this.cid = cid;
        this.cname = cname;
        this.cleader = cleader;
        this.createTime = createTime;
        this.updateTime = updateTime;
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
}
