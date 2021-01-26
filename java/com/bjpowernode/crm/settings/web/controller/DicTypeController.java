package com.bjpowernode.crm.settings.web.controller;

import com.bjpowernode.crm.commons.contants.Contants;
import com.bjpowernode.crm.commons.domain.ReturnObject;
import com.bjpowernode.crm.settings.domain.DicType;
import com.bjpowernode.crm.settings.service.DicTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * barry wang
 * 2020/12/25
 */

@Controller
public class DicTypeController {
    @Autowired
    DicTypeService dicTypeService;

    @RequestMapping("/settings/dictionary/type/index.do")
    public String index(Model model){
        List<DicType> dicTypeList = dicTypeService.selectAllDicType();
        model.addAttribute("dicTypeList", dicTypeList);

        return "settings/dictionary/type/index";


    }

    @RequestMapping("/settings/dictionary/type/toSave.do")
    public String toSave(){
        return "settings/dictionary/type/save";
    }

    @RequestMapping("/settings/dictionary/type/checkCode.do")
    public @ResponseBody Object checkCode(String code){
        DicType dicType = dicTypeService.selectDicTypeByCode(code);
        ReturnObject returnObject =new ReturnObject();
        if (dicType==null){
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
        }else {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("编码已存在");
        }


        return returnObject;
    }

    @RequestMapping("/settings/dictionary/type/saveCreateDicType.do")
    public @ResponseBody Object saveType(DicType dicType){
        ReturnObject returnObject =new ReturnObject();

        try {
            int ret = dicTypeService.insertDicType(dicType);
            if (ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("保存失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("保存失败");
        }

        return returnObject;
    }

    @RequestMapping("/settings/dictionary/type/deleteDicTypeByCodes.do")
    public @ResponseBody Object deleteTypeByCode(String[] code){
        ReturnObject returnObject =new ReturnObject();
        try {
            int ret = dicTypeService.deleteDicTypeByCodes(code);
            if (ret>0){
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


    @RequestMapping("/settings/dictionary/type/editDicType.do")
    public String toupdateTypeByCode(String code,Model model){
        DicType dicType = dicTypeService.selectDicTypeByCode(code);
        model.addAttribute("dicType",dicType);


        return "settings/dictionary/type/edit";

    }

    @RequestMapping("/settings/dictionary/type/saveEditDicType.do")
    public @ResponseBody Object updateTypeByCode(DicType dicType){
        ReturnObject returnObject =new ReturnObject();
        try {
            int set = dicTypeService.updateDicType(dicType);
            if (set>0){
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



}
