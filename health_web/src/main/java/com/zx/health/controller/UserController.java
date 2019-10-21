package com.zx.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zx.health.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Reference
    private UserService userService;



}
