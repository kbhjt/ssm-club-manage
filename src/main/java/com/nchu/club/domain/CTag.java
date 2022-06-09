package com.nchu.club.domain;

public class CTag {
    private int cid;
    private String ctag;

    public CTag() {}

    public CTag(int cid, String ctag) {
        this.cid = cid;
        this.ctag = ctag;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCtag() {
        return ctag;
    }

    public void setCtag(String ctag) {
        this.ctag = ctag;
    }
}
