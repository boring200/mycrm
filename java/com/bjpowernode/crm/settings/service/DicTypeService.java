package com.bjpowernode.crm.settings.service;

import com.bjpowernode.crm.settings.domain.DicType;

import java.util.List;

/**
 * barry wang
 * 2020/12/25
 */
public interface DicTypeService {

    List<DicType> selectAllDicType();

    //保存创建到数据字典类型

    int insertDicType(DicType dicType);


    //根据主键code查询数据字典类型

    DicType selectDicTypeByCode(String code);


    //更新

    int updateDicType(DicType dicType);


    int deleteDicTypeByCodes(String[] code);
}
