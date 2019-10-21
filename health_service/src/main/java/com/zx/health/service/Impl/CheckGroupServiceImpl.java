package com.zx.health.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zx.health.dao.CheckGroupMapper;
import com.zx.health.pojo.CheckGroup;
import com.zx.health.pojo.CheckItem;
import com.zx.health.pojo.PageResult;
import com.zx.health.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupMapper checkGroupMapper;

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage, pageSize);
        Page<CheckGroup> page = checkGroupMapper.findPage(queryString);
        long total = page.getTotal();
        return new PageResult(total, page.getResult());
    }

    @Override
    public List<CheckItem> findAllCheckItem() {
        List<CheckItem> list = checkGroupMapper.findAllCheckItem();
        return list;
    }

    @Override
    public void addCheckGroup(CheckGroup checkGroup) {
        checkGroupMapper.addCheckGroup(checkGroup);
        addCheckGroupIdAndCheckItemId(checkGroup.getId(), checkGroup.getCheckItemIds());
    }

    @Override
    public void deleteCheckGroupById(Integer id) {
        checkGroupMapper.deleteCheckGroupById(id);
        checkGroupMapper.deleteCheckItem(id);
    }

    @Override
    public CheckGroup findCheckGroupById(Integer id) {
        CheckGroup checkGroup = checkGroupMapper.findCheckGroupById(id);
        Integer[] checkItemIds = checkGroupMapper.findCheckItemIdByCheckGroupId(id);
        List<CheckItem> list = checkGroupMapper.findAllCheckItems();
        checkGroup.setCheckItems(list);
        checkGroup.setCheckItemIds(checkItemIds);
        return checkGroup;
    }

    @Override
    public void editCheckGroup(CheckGroup checkGroup) {
        checkGroupMapper.editCheckGroup(checkGroup);
        checkGroupMapper.deleteChecGroupIdAndCheckItemId(checkGroup.getId());
        addCheckGroupIdAndCheckItemId(checkGroup.getId(),checkGroup.getCheckItemIds());
    }

    /*private void editCheckGroupAndCheckItem(Integer id, Integer[] checkItemIds) {
        Map<String,Integer> map = new HashMap<>();
        if(checkItemIds!=null && checkItemIds.length >0){
            for (Integer checkItemId : checkItemIds) {
                map.put("checkGroupId",id);
                map.put("checkItemId",checkItemId);
                checkGroupMapper.editCheckGroupAndCheckItem(map);
            }
        }
    }*/

    private void addCheckGroupIdAndCheckItemId(Integer checkGroupId, Integer[] checkItemIds) {
        if (checkItemIds != null && checkItemIds.length > 0) {
            for (Integer checkItemId : checkItemIds) {
                Map<String, Integer> map = new HashMap<>();
                map.put("checkGroupId", checkGroupId);
                map.put("checkItemId", checkItemId);
                checkGroupMapper.setCheckGroupAndCheckItem(map);
            }
        }
    }


}
