package com.nchu.club.service.impl;

import com.nchu.club.dao.TypeDao;
import com.nchu.club.domain.Activity;
import com.nchu.club.domain.Type;
import com.nchu.club.service.ActivityTypeService;
import com.nchu.club.tablevo.ActivityTableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeServiceImpl implements ActivityTypeService {
    @Autowired
    private TypeDao typeDao;
    @Override
    public List<Type> getAllType() {
        List<Type> types = typeDao.selectAllType();
        return types;
    }

    @Override
    public void addType(Type type) {
        typeDao.addType(type);
    }

    @Override
    public int deleteType(int atid) {
        return typeDao.deleteType(atid);
    }

    @Override
    public Type getTypeByid(int atid) {
        return typeDao.selectTypeById(atid);
    }

    @Override
    public int updateType(Type type) {
        return typeDao.updateType(type.getAtid(),type);
    }

}
