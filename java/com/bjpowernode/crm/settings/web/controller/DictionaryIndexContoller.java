package com.bjpowernode.crm.settings.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * barry wang
 * 2020/12/25
 */
@Controller
public class DictionaryIndexContoller {

    @RequestMapping("/settings/dictionary/index.do")
    public String index(){
        return "settings/dictionary/index";
    }
}
