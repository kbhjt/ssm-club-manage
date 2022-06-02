package com.nchu.club.domain;

public class Apply {

    private int id;
    private int uid;
    private int cid;
    private String isOut;
    private int isDelete;

    public Apply() {}

    public Apply(int id, int uid, int cid, String isOut, int isDelete) {
        this.id = id;
        this.uid = uid;
        this.cid = cid;
        this.isOut = isOut;
        this.isDelete = isDelete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getIsOut() {
        return isOut;
    }

    public void setIsOut(String isOut) {
        this.isOut = isOut;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }


}
