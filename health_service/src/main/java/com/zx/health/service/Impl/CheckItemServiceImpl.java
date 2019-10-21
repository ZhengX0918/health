package com.zx.health.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zx.health.dao.CheckItemMapper;
import com.zx.health.pojo.CheckItem;
import com.zx.health.pojo.PageResult;
import com.zx.health.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemMapper checkItemMapper;
    @Override
    public boolean addCheckItem(CheckItem checkItem) {
        boolean result = checkItemMapper.addCheckItem(checkItem);
        return result;
    }



    @Override
    public PageResult findByCondition(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckItem> page = checkItemMapper.findByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public  void deleteCheckItemById(Integer id)throws RuntimeException {
       Long count =  checkItemMapper.findCountByCheckItemId(id);
       if(count >0){
           throw new RuntimeException("当前检查项被引用，不能删除");
       }
        checkItemMapper.deleteCheckItemById(id);
    }

    @Override
    public CheckItem findById(Integer id) {
       return checkItemMapper.findById(id);

    }

    @Override
    public void editCheckItem(CheckItem checkItem) {
        checkItemMapper.editCheckItem(checkItem);
    }
}
