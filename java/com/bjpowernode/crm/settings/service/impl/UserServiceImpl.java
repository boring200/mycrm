package com.bjpowernode.crm.settings.service.impl;

import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.mapper.UserMapper;
import com.bjpowernode.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * barry wang
 * 2020/12/22
 */

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserMapper userMapper;
    @Override
    public User selectUserByActAndPwd(Map<String, Object> map) {

        return userMapper.selectUserByLoginActAndPwd(map);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAllUsers();
    }
}
