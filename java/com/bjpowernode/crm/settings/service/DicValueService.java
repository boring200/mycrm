package com.bjpowernode.crm.settings.service;

import com.bjpowernode.crm.settings.domain.DicValue;

import java.util.List;

/**
 * barry wang
 * 2020/12/26
 */
public interface DicValueService {
    //查询全部
    List<DicValue> selectDicValueAll();


    //按id查询
    DicValue selectDicValueById(String id);

    //增加
    int insertDicValue(DicValue dicValue);

    //删除
    int deleteDicValueById(String[] id);

    //更新
    int updateDicValueById(DicValue dicValue);
}
