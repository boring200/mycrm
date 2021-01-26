package com.bjpowernode.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * barry wang
 * 2020/12/22
 */
@Controller
public class WorkbenchIndexController {

    @RequestMapping("/workbench/index.do")

    public String index(){
        return "workbench/index";
    }

    @RequestMapping("/workbench/activity/index.do")

    public String indexActivity(){
        return "workbench/activity/index";
    }
}
