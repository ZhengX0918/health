package com.zx.health.service;

import com.zx.health.pojo.CheckGroup;
import com.zx.health.pojo.CheckItem;
import com.zx.health.pojo.PageResult;

import java.util.List;

public interface CheckGroupService {

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    List<CheckItem> findAllCheckItem();

    void addCheckGroup(CheckGroup checkGroup);

    void deleteCheckGroupById(Integer id);

    CheckGroup findCheckGroupById(Integer id);

    void editCheckGroup(CheckGroup checkGroup);
}
