package com.zx.health.dao;

import com.github.pagehelper.Page;
import com.zx.health.pojo.CheckGroup;
import com.zx.health.pojo.CheckItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface CheckGroupMapper {

    /*
    * /**
     * map & pojo 不需要加@Param
     * 多参数建议加@Param ,不加就需要按照param1 param2 ...paramN
     * List & Array 可以不加@Param  如果不加@Param取值需要写list&array
     * 如果有多个List参数那么取值  param1 param2 ...paramN
     */

    Page<CheckGroup> findPage(@Param("queryString") String queryString);

    List<CheckItem> findAllCheckItem();

    Integer addCheckGroup(CheckGroup checkGroup);

//    void addCheckGroupIdAndCheckItemId(@Param("checkGroupId") Integer checkGroupId, @Param("array") Integer[] checkItemIds);

    void setCheckGroupAndCheckItem(Map<String, Integer> map);

    void deleteCheckGroupById(@Param("id") Integer id);

    void deleteCheckItem(@Param("id") Integer id);

    CheckGroup findCheckGroupById(@Param("id") Integer id);

    Integer[] findCheckItemIdByCheckGroupId(@Param("id") Integer id);

    CheckItem findCheckItemById(@Param("id") Integer id);

    void editCheckGroup(CheckGroup checkGroup);

//    void editCheckGroupAndCheckItem(Map<String, Integer> map);

    List<CheckItem> findAllCheckItems();

    void deleteChecGroupIdAndCheckItemId(@Param("id") Integer id);
}
