package com.zx.health.service;

import com.zx.health.pojo.*;

import java.util.List;

public interface SetMealService {

    PageResult findPage(QueryPageBean queryPageBean);

    List<CheckGroup> findAllCheckGroup();

    void addSetMeal(Setmeal setmeal);

    void deleteSetMealById(Integer id);


    List<Setmeal> findAllSetMeals();

    Setmeal findSetMealById(Integer id);

    Setmeal findDetailsById(Integer id);
}
