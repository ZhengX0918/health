package com.zx.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zx.health.commons.MessageConstant;
import com.zx.health.pojo.CheckItem;
import com.zx.health.pojo.PageResult;
import com.zx.health.pojo.QueryPageBean;
import com.zx.health.pojo.Result;
import com.zx.health.service.CheckItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "checkItem")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;

    @RequestMapping(path = "/add")
    public @ResponseBody boolean addCheckItem( @RequestBody CheckItem checkItem){
        boolean result = checkItemService.addCheckItem(checkItem);
        return result;
    }

    @RequestMapping(path = "/findByCondition")
    public @ResponseBody PageResult findByCondition(Integer currentPage,Integer pageSize,String queryString){
        QueryPageBean queryPageBean = new QueryPageBean(currentPage,pageSize,queryString);
        PageResult pageResult =  checkItemService.findByCondition(queryPageBean.getCurrentPage(),queryPageBean.getPageSize(),queryPageBean.getQueryString());

        return pageResult;
    }

    @RequestMapping(path = "/deleteCheckItemById")
    public @ResponseBody Result deleteCheckItemById(Integer id){
//        System.out.println(id);
        try {
             checkItemService.deleteCheckItemById(id);
        } catch (RuntimeException e){
            return new Result(false,e.getMessage());
        }catch (Exception e){
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    @RequestMapping(path = "/findById")
    public @ResponseBody Result findById(Integer id){
        CheckItem checkItem =null;
        try {
             checkItem = checkItemService.findById(id);
        } catch (Exception e) {
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
    }

    @RequestMapping(path = "/edit")
    public @ResponseBody Result editCheckItem(@RequestBody CheckItem checkItem){
        try {
            checkItemService.editCheckItem(checkItem);
            return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS,checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKITEM_FAIL);
        }
    }

}
