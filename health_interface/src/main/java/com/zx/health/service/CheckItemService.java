package com.zx.health.service;

import com.zx.health.pojo.CheckItem;
import com.zx.health.pojo.PageResult;

public interface CheckItemService {
    /*
     添加检查项
    * */
    boolean addCheckItem(CheckItem checkItem);
    /*
      按照条件查询
     *查询每页显示的数据
     * */
    PageResult findByCondition(Integer currentPage, Integer pageSize, String queryString);

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    void deleteCheckItemById(Integer id);

    /**根据id查询数据
     * @param id
     * @return
     */
    CheckItem findById(Integer id);

    /**genjid更改查询项信息
     * @param checkItem
     */
    void editCheckItem(CheckItem checkItem);
}
