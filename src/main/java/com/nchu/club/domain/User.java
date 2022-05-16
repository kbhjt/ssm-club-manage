package com.nchu.club.domain;

public class User {

    private Integer uid;     //用户id
    private String uname;   //用户姓名
    private String uemail;  //用户邮箱
    private String uclass;  //用户班级
    private String unick;   //用户昵称
    private String uimage;  //用户头像
    private String usign;   //用户个性签名
    private String usex;    //用户性别
    private String upassword; //用户密码
    private int roleId; //用户角色
    private String uno; //用户学号

    public User() {}

    public User(Integer uid, String uname, String uemail, String uclass, String unick, String uimage, String usign, String usex, String upassword, int roleId) {
        this.uid = uid;
        this.uname = uname;
        this.uemail = uemail;
        this.uclass = uclass;
        this.unick = unick;
        this.uimage = uimage;
        this.usign = usign;
        this.usex = usex;
        this.upassword = upassword;
        this.roleId = roleId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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

    public String getUclass() {
        return uclass;
    }

    public void setUclass(String uclass) {
        this.uclass = uclass;
    }

    public String getUnick() {
        return unick;
    }

    public void setUnick(String unick) {
        this.unick = unick;
    }

    public String getUimage() {
        return uimage;
    }

    public void setUimage(String uimage) {
        this.uimage = uimage;
    }

    public String getUsign() {
        return usign;
    }

    public void setUsign(String usign) {
        this.usign = usign;
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUno() {
        return uno;
    }

    public void setUno(String uno) {
        this.uno = uno;
    }
}
