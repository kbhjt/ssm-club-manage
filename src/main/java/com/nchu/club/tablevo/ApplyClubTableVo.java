package com.nchu.club.tablevo;

import com.nchu.club.vo.ApplyClubVo;

import java.util.List;

public class ApplyClubTableVo {
    private Integer code = 0;
    private Integer count;
    private List<ApplyClubVo> data;

    public ApplyClubTableVo() {}

    public ApplyClubTableVo(Integer code, Integer count, List<ApplyClubVo> data) {
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

    public List<ApplyClubVo> getData() {
        return data;
    }

    public void setData(List<ApplyClubVo> data) {
        this.data = data;
    }
}
