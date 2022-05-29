package com.nchu.club.vo;

public class ApplyClubVo {
    private int id;
    private int uno;
    private int uid;
    private String uname;
    private String uemail;
    private String isOut;  //是加入还是退出

    public ApplyClubVo() {}

    public ApplyClubVo(int id, int uno, int uid, String uname, String uemail, String isOut) {
        this.id = id;
        this.uno = uno;
        this.uid = uid;
        this.uname = uname;
        this.uemail = uemail;
        this.isOut = isOut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUno() {
        return uno;
    }

    public void setUno(int uno) {
        this.uno = uno;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getIsOut() {
        return isOut;
    }

    public void setIsOut(String isOut) {
        this.isOut = isOut;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
