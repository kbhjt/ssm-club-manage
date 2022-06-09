package com.nchu.club.tablevo;

import com.nchu.club.domain.Type;

import java.util.List;

public class TypeTableVo {
    private Integer code = 0;
    private Integer count;
    private List<Type> data;

    public TypeTableVo(Integer code, Integer count, List<Type> data) {
        this.code = code;
        this.count = count;
        this.data = data;
    }

    public TypeTableVo() {
        super();
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

    public List<Type> getData() {
        return data;
    }

    public void setData(List<Type> data) {
        this.data = data;
    }
}





