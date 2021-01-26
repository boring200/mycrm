package com.bjpowernode.crm.settings.service.impl;

import com.bjpowernode.crm.settings.domain.DicType;
import com.bjpowernode.crm.settings.domain.DicValue;
import com.bjpowernode.crm.settings.mapper.DicTypeMapper;
import com.bjpowernode.crm.settings.service.DicTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * barry wang
 * 2020/12/25
 */

@Service
public class DicTypeServiceImpl implements DicTypeService {

    @Autowired
    DicTypeMapper dicTypeMapper;

    @Override
    public List<DicType> selectAllDicType() {
        return dicTypeMapper.selectAllDicType();


    }

    @Override
    public int insertDicType(DicType dicType) {
        return dicTypeMapper.insertDicType(dicType);
    }

    @Override
    public DicType selectDicTypeByCode(String code) {
        return dicTypeMapper.selectDicTypeByCode(code);
    }

    @Override
    public int updateDicType(DicType dicType) {
        return dicTypeMapper.updateDicType(dicType);
    }

    @Override
    public int deleteDicTypeByCodes(String[] code) {
        return dicTypeMapper.deleteDicTypeByCodes(code);
    }
}
