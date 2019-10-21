package com.zx.health.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zx.health.dao.OrderSettingMapper;
import com.zx.health.pojo.OrderSetting;
import com.zx.health.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingMapper orderSettingMapper;
    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
       Long count =  orderSettingMapper.findCountByOrderDate(orderSetting.getOrderDate());
       if(count>0) {
           orderSettingMapper.editNumberByDate(orderSetting);
       }else{
           orderSetting.setReservations(0);
           orderSettingMapper.add(orderSetting);
       }
    }

    @Override
    public void addOrderSetting(List<OrderSetting> orderSettingList) {
        if(orderSettingList!=null && orderSettingList.size() >0){
            for (OrderSetting orderSetting : orderSettingList) {
                Date orderDate = orderSetting.getOrderDate();
                Long count = orderSettingMapper.findCountByOrderDate(orderDate);
                if(count>0){
                    orderSettingMapper.editNumberByDate(orderSetting);
                }else{
//                    orderSetting.setReservations(0);
                    orderSettingMapper.add(orderSetting);
                }
            }
        }
    }

    @Override
    public List<Map> findOrderSettingsByMonth(String formatedDate) {
        String beginDate  = formatedDate +"-01";
        String endDate = formatedDate +"-31";
        Map map = new HashMap<>();
        map.put("beginDate",beginDate);
        map.put("endDate",endDate);
        List<OrderSetting> list = orderSettingMapper.findOrderSettingsByMonth(map);
        List<Map> data = new ArrayList<>();
        for (OrderSetting orderSetting : list) {
            Map orderSettingMap = new HashMap();
            orderSettingMap.put("date",orderSetting.getOrderDate().getDate());//获得日期（几号）
            orderSettingMap.put("mouth",orderSetting.getOrderDate().getMonth());
            orderSettingMap.put("number",orderSetting.getNumber());//可预约人数
            orderSettingMap.put("reservations",orderSetting.getReservations());//已预约人数
            data.add(orderSettingMap);
        }
        return data;
    }
}
