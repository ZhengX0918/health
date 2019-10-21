package com.zx.health.service.Impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zx.health.dao.SetMealMapper;
import com.zx.health.pojo.*;
import com.zx.health.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Service(interfaceClass = SetMealService.class)
public class SetMealServiceImpl implements SetMealService {

    @Autowired
    private SetMealMapper setMealMapper;

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
          PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<Setmeal>page = setMealMapper.findPage(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public List<CheckGroup> findAllCheckGroup() {
        List<CheckGroup> list = setMealMapper.findAllCheckGroup();
        return list;
    }

    @Override
    public void addSetMeal(Setmeal setmeal) {
        setMealMapper.addSetMeal(setmeal);
        setSetmealAndCheckGroup(setmeal.getId(),setmeal.getCheckGroupIds());
    }

    @Override
    public void deleteSetMealById(Integer id) {
        setMealMapper.deleteSetMealAndCheckGroupById(id);
        setMealMapper.deleteSetMealById(id);
    }

    @Override
    public List<Setmeal> findAllSetMeals() {
        List<Setmeal> list =  setMealMapper.findAllSetMeals();
        return list;
    }

    @Override
    public Setmeal findSetMealById(Integer id) {
        Setmeal setmeal = setMealMapper.findSetMealById(id);
        return setmeal;
    }

    @Override
    public Setmeal findDetailsById(Integer id) {
        List<Integer> ids = setMealMapper.findCheckGroupIds(id);
        List<CheckGroup> list = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(ids)) {
            for (Integer gid : ids) {
                CheckGroup checkGroup = setMealMapper.findDetailById(gid);
               List<Integer> itemIds =  setMealMapper.findCheckItemIds(gid);
               List<CheckItem> items = new ArrayList<>();
                for (Integer itemId : itemIds) {
                    CheckItem checkItem = setMealMapper.findCheckItem(itemId);
                    items.add(checkItem);
                }
                checkGroup.setCheckItems(items);
                list.add(checkGroup);
            }
        }
        Setmeal setmeal = new Setmeal();
        setmeal.setCheckGroups(list);
        return setmeal;
    }


    private void setSetmealAndCheckGroup(Integer id, Integer[] checkGroupIds) {
        Map<String,Integer> map = new HashMap<>();
        if(checkGroupIds!=null && checkGroupIds.length>0){
            for (Integer checkGroupId : checkGroupIds) {
                map.put("setMealId",id);
                map.put("checkGroupId",checkGroupId);
                setMealMapper.addSetmealIdAndCheckGroupId(map);
            }
        }
    }
}
