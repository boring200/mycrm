package com.bjpowernode.crm.settings.service.impl;

import com.bjpowernode.crm.settings.domain.DicValue;
import com.bjpowernode.crm.settings.mapper.DicValueMapper;
import com.bjpowernode.crm.settings.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * barry wang
 * 2020/12/26
 */

@Service
public class DicValueServiceImpl implements DicValueService {

    @Autowired
    DicValueMapper dicValueMapper;
    @Override
    public List<DicValue> selectDicValueAll() {
        return dicValueMapper.selectDicValueAll();
    }

    @Override
    public DicValue selectDicValueById(String id) {
        return dicValueMapper.selectDicValueById(id);
    }

    @Override
    public int insertDicValue(DicValue dicValue) {
        return dicValueMapper.insertDicValue(dicValue);
    }

    @Override
    public int deleteDicValueById(String[] id) {
        return dicValueMapper.deleteDicValueById(id);
    }

    @Override
    public int updateDicValueById(DicValue dicValue) {
        return dicValueMapper.updateDicValueById(dicValue);
    }
}
