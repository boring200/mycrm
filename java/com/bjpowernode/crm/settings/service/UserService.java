package com.bjpowernode.crm.settings.service;

import com.bjpowernode.crm.settings.domain.User;

import java.util.List;
import java.util.Map;

/**
 * barry wang
 * 2020/12/22
 */
public interface UserService {

    User selectUserByActAndPwd(Map<String,Object> map);

    List<User> selectAll();
}
