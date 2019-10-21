package com.zx.health.service;

import com.zx.health.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

public interface OrderSettingService {
    void editNumberByDate(OrderSetting orderSetting);

    void addOrderSetting(List<OrderSetting> orderSettingList);

    List<Map> findOrderSettingsByMonth(String formatedDate);
}
