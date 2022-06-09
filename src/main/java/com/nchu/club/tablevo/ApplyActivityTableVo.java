package com.nchu.club.tablevo;

import com.nchu.club.domain.Apply;

import java.util.List;

public class ApplyActivityTableVo {
    private Integer code = 0;
    private Integer count;
    private List<Apply> data;

    public ApplyActivityTableVo() {}

    public ApplyActivityTableVo(Integer code, Integer count, List<Apply> data) {
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

    public List<Apply> getData() {
        return data;
    }

    public void setData(List<Apply> data) {
        this.data = data;
    }
}
