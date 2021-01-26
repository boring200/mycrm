package com.bjpowernode.crm.settings.web.controller;

import com.bjpowernode.crm.commons.contants.Contants;
import com.bjpowernode.crm.commons.domain.ReturnObject;
import com.bjpowernode.crm.commons.utils.UUIDUtils;
import com.bjpowernode.crm.settings.domain.DicType;
import com.bjpowernode.crm.settings.domain.DicValue;
import com.bjpowernode.crm.settings.service.DicTypeService;
import com.bjpowernode.crm.settings.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * barry wang
 * 2020/12/26
 */

@Controller
public class DicValueController {
    @Autowired
    DicValueService dicValueService;

    @Autowired
    DicTypeService dicTypeService;


    @RequestMapping("/settings/dictionary/value/index.do")
    public String selectAllDicValue(Model model){
        List<DicValue> dicValueList = dicValueService.selectDicValueAll();
        model.addAttribute("dicValueList",dicValueList);

        return "settings/dictionary/value/index";
    }


    @RequestMapping("/settings/dictionary/value/toSave.do")
    public String toSaveDicValue(Model model){
        List<DicType> dicTypeList =dicTypeService.selectAllDicType();
        model.addAttribute("dicTypeList",dicTypeList);
        return "settings/dictionary/value/save";
    }

    @RequestMapping("/settings/dictionary/value/saveCreateDicValue.do")
    public @ResponseBody Object insertCreateDicValue(DicValue dicValue){
        dicValue.setId(UUIDUtils.getUUID());
        ReturnObject returnObject =new ReturnObject();
        try {
            int ret = dicValueService.insertDicValue(dicValue);
            if (ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else{
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("新建失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("新建失败");
        }
        return returnObject;


    }
    @RequestMapping("/settings/dictionary/value/editDicValue.do")
    public String toEditDicValue(String id,Model model){
        DicValue dicValue = dicValueService.selectDicValueById(id);
        model.addAttribute("dicValue",dicValue);
        return "settings/dictionary/value/edit";
    }

    @RequestMapping("/settings/dictionary/value/saveEditDicValue.do")
    public @ResponseBody Object saveEditDicValue(DicValue dicValue){
        ReturnObject returnObject = new ReturnObject();
        try {
            int ret = dicValueService.updateDicValueById(dicValue);
            if (ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("更新失败");
        }
        return returnObject;
    }

    @RequestMapping("/settings/dictionary/value/deleteDicValueByIds.do")
    public @ResponseBody Object deleteDicValueById(String[] id){
        ReturnObject returnObject =new ReturnObject();
        try {
            int ret = dicValueService.deleteDicValueById(id);
            if(ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("删除失败");
        }

        return returnObject;
    }
}
