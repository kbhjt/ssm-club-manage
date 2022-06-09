package com.nchu.club.service;

import com.nchu.club.domain.Activity;
import com.nchu.club.domain.Type;
import com.nchu.club.tablevo.ActivityTableVo;
import com.nchu.club.vo.ActivityDataVo;
import com.nchu.club.vo.ActivityPeopleNumVo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ActivityTypeService {
    //查询社团类型
    List<Type> getAllType();
    //添加活动类型
    void addType(Type type);
    //删除活动类型
    int deleteType(int atid);
    //查找指定活动类型
    Type getTypeByid(int atid);
    //修改活动信息
    int updateType(Type type);
}

