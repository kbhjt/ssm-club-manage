package com.nchu.club.service;

import com.nchu.club.domain.Apply;
import java.util.List;

public interface ApplyActivityService {
    //社团负责人或者社团助手查询审批单
    List<Apply> findApply(int cid);
    //同意申请
    int  agreeApply(int applyid);
    //不同意申请
    int  disagreeApply(int applyid);
}