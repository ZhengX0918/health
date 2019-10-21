package com.zx.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jcraft.jsch.SftpException;
import com.zx.health.commons.*;
import com.zx.health.pojo.*;
import com.zx.health.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(path = "/setMeal")
public class SetMealController {
    @Autowired
    private JedisPool jedisPool;

    @Reference
    private SetMealService setMealService;

    @RequestMapping(path = "/findPage")
    public @ResponseBody PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = setMealService.findPage(queryPageBean);
        return pageResult;
    }

    @RequestMapping(path = "/findAllCheckGroup")
    public @ResponseBody Result findAllCheckGroup(){
        List<CheckGroup> list =null;
        try {
             list = setMealService.findAllCheckGroup();
             return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping(path = "/addSetMeal")
    public @ResponseBody Result addSetMeal(@RequestBody Setmeal setmeal){
        try {
            setMealService.addSetMeal(setmeal);
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());
            return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }
    }
    @RequestMapping(path = "/deleteSetMealById")
    public @ResponseBody Result deleteSetMealById(Integer id){
        try {
            setMealService.deleteSetMealById(id);
            return new Result(true,MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping(path = "/upload")
    public @ResponseBody Result upload(@RequestParam("imgFile") MultipartFile imgFile){

        try {
            String originalFilename = imgFile.getOriginalFilename();
            int lastIndexOf = originalFilename.lastIndexOf(".");
            String substring = originalFilename.substring(lastIndexOf);
            String filename = UUID.randomUUID().toString() + substring;
           /* SftpUtils sftpUtils = new SftpUtils();
            InputStream inputStream = imgFile.getInputStream();
            sftpUtils.login();
            sftpUtils.upload("/home/images","/zx",filename,inputStream);*/
//            QiniuUtils.upload2Qiniu(imgFile.getBytes(),filename);
            QiniuUtil.upload(imgFile.getBytes(),filename);
            Result result = new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS);
            result.setData(filename);
//            sftpUtils.logout();
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,filename);
            return result;
        } catch ( Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }



}
