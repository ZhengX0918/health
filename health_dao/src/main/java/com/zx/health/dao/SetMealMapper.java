package com.zx.health.dao;

import com.github.pagehelper.Page;
import com.zx.health.pojo.CheckGroup;
import com.zx.health.pojo.CheckItem;
import com.zx.health.pojo.OrderSetting;
import com.zx.health.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public interface SetMealMapper {
    Page<Setmeal> findPage(@Param("queryString") String queryString);

    List<CheckGroup> findAllCheckGroup();

    void addSetMeal(Setmeal setmeal);

    void addSetmealIdAndCheckGroupId(Map<String, Integer> map);

    void deleteSetMealAndCheckGroupById(@Param("id") Integer id);

    void deleteSetMealById(@Param("id") Integer id);


    List<Setmeal> findAllSetMeals();

    Setmeal findSetMealById(Integer id);

    List<Integer> findCheckGroupIds(@Param("id") Integer id);

    CheckGroup findDetailById(@Param("id") Integer gid);

    List<Integer> findCheckItemIds(@Param("id") Integer gid);

    CheckItem findCheckItem(@Param("id") Integer itemId);
}
