package com.nchu.club.domain;

public class Club {

    private String cid;         //社团id
    private String cname;       //社团名称
    private String cintroduce;  //社团介绍
    private String cleader;     //社团负责人
    private String chelper;     //社团助理
    private String createTime;  //创建时间
    private String updateTime;  //修改时间
    private int isDelete;         //删除标记位（假删除）

    public Club() {}

    public Club(String cid, String cname, String cintroduce, String cleader, String chelper, String createTime, String updateTime, int delete) {
        this.cid = cid;
        this.cname = cname;
        this.cintroduce = cintroduce;
        this.cleader = cleader;
        this.chelper = chelper;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isDelete = delete;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCintroduce() {
        return cintroduce;
    }

    public void setCintroduce(String cintroduce) {
        this.cintroduce = cintroduce;
    }

    public String getCleader() {
        return cleader;
    }

    public void setCleader(String cleader) {
        this.cleader = cleader;
    }

    public String getChelper() {
        return chelper;
    }

    public void setChelper(String chelper) {
        this.chelper = chelper;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
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
