package com.nchu.club.vo;

import java.util.List;

public class ClubUserTableVo {
    private Integer code = 0;
    private Integer count;
    private List<ClubUserVo> data;

    public ClubUserTableVo() {}

    public ClubUserTableVo(Integer code, Integer count, List<ClubUserVo> data) {
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

    public List<ClubUserVo> getData() {
        return data;
    }

    public void setData(List<ClubUserVo> data) {
        this.data = data;
    }
}
