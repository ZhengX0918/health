package com.zx.health.dao;

import com.github.pagehelper.Page;
import com.zx.health.pojo.CheckItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckItemMapper {
    boolean addCheckItem(CheckItem checkItem);

    Page<CheckItem> findByCondition(@Param("queryString") String queryString);

    void deleteCheckItemById(@Param("id") Integer id);

    Long findCountByCheckItemId(@Param("checkItemId") Integer id);

    CheckItem findById(@Param("id") Integer id);

    void editCheckItem(CheckItem checkItem);
}
