package com.nchu.club.vo;

public class ClubUserVo {
    private int uid;
    private String uno;
    private String uname;
    private String roleName;
    private String uemail;

    public ClubUserVo() {}

    public ClubUserVo(int uid, String uno, String uname, String roleName, String uemail) {
        this.uid = uid;
        this.uno = uno;
        this.uname = uname;
        this.roleName = roleName;
        this.uemail = uemail;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUno() {
        return uno;
    }

    public void setUno(String uno) {
        this.uno = uno;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }
}
