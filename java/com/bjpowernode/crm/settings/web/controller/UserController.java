package com.bjpowernode.crm.settings.web.controller;

import com.bjpowernode.crm.commons.contants.Contants;
import com.bjpowernode.crm.commons.domain.ReturnObject;
import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.commons.utils.MD5Util;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * barry wang
 * 2020/12/22
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin(HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        String loginAct =null;
        String loginPwd =null;
        for (Cookie cookie : cookies) {
            String name =cookie.getName();
            if ("loginAct".equals(name)){
                loginAct =cookie.getValue();
                continue;
            }
            if("loginPwd".equals(name)){
                loginPwd =cookie.getValue();
            }

        }
        if (loginAct!=null&&loginPwd!=null){
            Map<String,Object> map =new HashMap<>();

            map.put("loginAct",loginAct);
            map.put("loginPwd",MD5Util.getMD5(loginPwd));
            User user =userService.selectUserByActAndPwd(map);
            request.getSession().setAttribute("sessionUser", user);
            return "redirect:/workbench/index.do";

        }else {
            return "settings/qx/user/login";
        }


    }


    @RequestMapping("/settings/qx/user/login.do")
    public @ResponseBody Object login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request, HttpServletResponse response, HttpSession session){

        Map<String,Object> map =new HashMap<>();

        map.put("loginAct",loginAct);
        map.put("loginPwd",MD5Util.getMD5(loginPwd));
        User user =userService.selectUserByActAndPwd(map);
        ReturnObject returnObject =new ReturnObject();
        //根据查询结果，生成返回对象相关信息
        if(user==null){
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("用户名或密码错误");
        }else{
            if(DateUtils.formatDateTime(new Date()).compareTo(user.getExpireTime())>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("账号时间已过期");


            }else if("0".equals(user.getLockState())){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("账号被锁定");


                //request.getRemoteAddr()拿到当前登陆人的ip地址
            /*}else if (!user.getAllowIps().contains(request.getRemoteAddr())){
                System.out.println(request.getRemoteAddr());
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("ip受限");*/

            }else{
                //登陆成功
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
                //把user保存到session以便于后期登陆判断
                session.setAttribute(Contants.SESSION_USER,user);

                //是否需要面登陆功能
                if("true".equals(isRemPwd)){
                    Cookie c1 =new Cookie("loginAct", loginAct);
                    //设置保存时间
                    c1.setMaxAge(10*24*60*60);
                    response.addCookie(c1);
                    Cookie c2 =new Cookie("loginPwd", loginPwd);
                    c2.setMaxAge(10*24*60*60);
                    response.addCookie(c2);
                }else {
                    Cookie c1 =new Cookie("loginAct", null);
                    //设置保存时间
                    c1.setMaxAge(0);
                    response.addCookie(c1);
                    Cookie c2 =new Cookie("loginPwd", null);
                    c2.setMaxAge(0);
                    response.addCookie(c2);

                }
            }

        }


        return returnObject;
    }

    //退出功能
    @RequestMapping("/settings/qx/user/logout.do")
    public String logout(HttpServletRequest request,HttpServletResponse response,HttpSession session){

        Cookie c1 =new Cookie("loginAct", null);

        c1.setMaxAge(0);//不保存cookie
        response.addCookie(c1);
        Cookie c2 =new Cookie("loginPwd", null);
        c2.setMaxAge(0);
        response.addCookie(c2);
        //销毁session
        session.invalidate();
        return "redirect:/";

    }
}
