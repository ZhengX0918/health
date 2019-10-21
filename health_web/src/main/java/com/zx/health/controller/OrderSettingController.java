package com.zx.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zx.health.commons.MessageConstant;
import com.zx.health.commons.POIUtils;
import com.zx.health.pojo.OrderSetting;
import com.zx.health.pojo.Result;
import com.zx.health.service.OrderSettingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(path = "/orderSetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    @RequestMapping(path = "/editNumberByDate")
    public @ResponseBody Result editNumberByDate(@RequestBody OrderSetting orderSetting){

        try {
            orderSettingService.editNumberByDate(orderSetting);
            return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }
    }

    @RequestMapping(path = "/upload")
    public @ResponseBody Result upload(@RequestParam("excelFile") MultipartFile excelFile) throws Exception {
        try {
        List<String[]> list = POIUtils.readExcel(excelFile);
        List<OrderSetting> orderSettingList = new ArrayList<>();
        if(list.size() > 0){
            for (String[] strings : list) {
                if(strings!=null && !("").equals(strings[0])) {
                    Date date = new Date(strings[0]);
                    System.out.println(date);
                    int number = Integer.parseInt(strings[1]);
                    OrderSetting orderSetting = new OrderSetting(date, number);
                    orderSettingList.add(orderSetting);
                }
            }
        }
            orderSettingService.addOrderSetting(orderSettingList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
            return new Result(true,MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
    }

    @RequestMapping(path = "/findOrderSettingsByMonth")
    public @ResponseBody Result findOrderSettingsByMonth(@RequestParam("formatedDate") String formatedDate){

        try {
            List<Map> list = orderSettingService.findOrderSettingsByMonth(formatedDate);
            return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_ORDERSETTING_FAIL);
        }

    }
}
