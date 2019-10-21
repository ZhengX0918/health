package com.zx.health.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zx.health.dao.UserMapper;
import com.zx.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class UserServcieImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


}
