package com.zx.health.dao;

import com.zx.health.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface OrderSettingMapper {
    void editNumberByDate(OrderSetting orderSetting);

    Long findCountByOrderDate(@Param("orderDate") Date orderDate);

    void add(OrderSetting orderSetting);

    List<OrderSetting> findOrderSettingsByMonth(Map map);
}
