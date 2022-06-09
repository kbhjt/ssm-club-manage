package com.nchu.club.domain;

public class Type {
    private Integer atid;
    private String type;
    private int number;
    private double time;

    public Type(){

    }
    public Type(Integer atid, String type, int number, double time) {
        this.atid = atid;
        this.type = type;
        this.number = number;
        this.time = time;
    }
    public Integer getAtid() {
        return atid;
    }

    public void setAtid(Integer aid) {
        this.atid = aid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }
}