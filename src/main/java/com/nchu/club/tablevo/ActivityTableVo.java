package com.nchu.club.tablevo;

import com.nchu.club.domain.Activity;
import java.util.List;

public class ActivityTableVo {

    private Integer code = 0;
    private Integer count;
    private List<Activity> data;

    public ActivityTableVo() {}

    public ActivityTableVo(Integer code, Integer count, List<Activity> data) {
        this.code = code;
        this.count = count;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Activity> getData() {
        return data;
    }

    public void setData(List<Activity> data) {
        this.data = data;
    }
}
