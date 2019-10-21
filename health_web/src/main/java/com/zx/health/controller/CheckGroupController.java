package com.zx.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sun.org.apache.regexp.internal.RE;
import com.zx.health.commons.MessageConstant;
import com.zx.health.pojo.*;
import com.zx.health.service.CheckGroupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "/checkGroup")
public class CheckGroupController {

    @Reference
    private CheckGroupService checkGroupService;

    @RequestMapping(path = "/findPage")
    public @ResponseBody PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = checkGroupService.findPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize(),queryPageBean.getQueryString());
        return pageResult;
    }

    @RequestMapping(path = "/findAllCheckItem")
    public @ResponseBody Result findAllCheckItem(){
        List<CheckItem> list =null;
        try {
             list = checkGroupService.findAllCheckItem();
             return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    @RequestMapping(path = "/addCheckGroup")
    public @ResponseBody Result addCheckGroup(@RequestBody CheckGroup checkGroup){
        try {
            checkGroupService.addCheckGroup(checkGroup);
            return new Result(true,MessageConstant.ADD_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_CHECKGROUP_FAIL);
        }
    }
    @RequestMapping(path = "/deleteCheckGroupById")
    public @ResponseBody Result deleteCheckGroupById(@RequestParam("id") Integer id){
        try {
            checkGroupService.deleteCheckGroupById(id);
            return new Result(true,MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("/findCheckGroupById")
    public @ResponseBody Result findCheckGroupById(Integer id){
        try {
            CheckGroup checkGroup = checkGroupService.findCheckGroupById(id);
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }
    @RequestMapping(path = "/editCheckGroup")
    public @ResponseBody Result editCheckGroup(@RequestBody CheckGroup checkGroup){
        try {
            checkGroupService.editCheckGroup(checkGroup);
            return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }
}
