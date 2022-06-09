package com.nchu.club.service.impl;

import com.nchu.club.dao.ApplyActivityDao;
import com.nchu.club.domain.Apply;
import com.nchu.club.service.ApplyActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApplyActivityServiceImpl implements ApplyActivityService {
    @Autowired
    private ApplyActivityDao applyActivityDao;

    @Override
    public List<Apply> findApply(int cid) {
        return applyActivityDao.getAllApplyActivity(cid);
    }

    @Override
    public int agreeApply(int applyid) {
       return applyActivityDao.agreeApply(applyid);
    }

    @Override
    public int disagreeApply(int applyid) {
       return applyActivityDao.disagreeApply(applyid);
    }
}