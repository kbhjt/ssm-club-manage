package com.nchu.club.tablevo;

import com.nchu.club.vo.CMessageVo;
import java.util.List;

public class CMessageTableVo {
    private Integer code = 0;
    private Integer count;
    private List<CMessageVo> data;

    public CMessageTableVo() {}

    public CMessageTableVo(Integer code, Integer count, List<CMessageVo> data) {
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

    public List<CMessageVo> getData() {
        return data;
    }

    public void setData(List<CMessageVo> data) {
        this.data = data;
    }
}
