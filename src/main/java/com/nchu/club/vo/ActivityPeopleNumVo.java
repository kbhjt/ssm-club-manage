package com.nchu.club.vo;

public class ActivityPeopleNumVo {
    private int value;
    private String name;


    public ActivityPeopleNumVo() {}

    public ActivityPeopleNumVo(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
