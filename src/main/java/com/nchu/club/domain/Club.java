package com.nchu.club.domain;

public class Club {

    private Integer cid;         //社团id
    private String cname;       //社团名称
    private String cintroduce;  //社团介绍
    private Integer cleader;     //社团负责人
    private Integer chelper;     //社团助理
    private String createTime;  //创建时间
    private String updateTime;  //修改时间
    private String cimage; //社团图片
    private int isDelete;         //删除标记位（假删除）

    public Club() {}

    public Club(Integer cid, String cname, String cintroduce, Integer cleader, Integer chelper, String createTime, String updateTime, String cimage, int isDelete) {
        this.cid = cid;
        this.cname = cname;
        this.cintroduce = cintroduce;
        this.cleader = cleader;
        this.chelper = chelper;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.cimage = cimage;
        this.isDelete = isDelete;
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

    public String getCintroduce() {
        return cintroduce;
    }

    public void setCintroduce(String cintroduce) {
        this.cintroduce = cintroduce;
    }

    public Integer getCleader() {
        return cleader;
    }

    public void setCleader(Integer cleader) {
        this.cleader = cleader;
    }

    public Integer getChelper() {
        return chelper;
    }

    public void setChelper(Integer chelper) {
        this.chelper = chelper;
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

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
