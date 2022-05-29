package com.nchu.club.tablevo;

import com.nchu.club.vo.ClubVo;

import java.util.List;

public class ClubTableVo {
    private Integer code = 0;
    private Integer count;
    private List<ClubVo> data;

    public ClubTableVo() {}

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<ClubVo> getData() {
        return data;
    }

    public void setData(List<ClubVo> data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
