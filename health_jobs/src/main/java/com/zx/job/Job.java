package com.zx.job;

import cn.hutool.core.collection.CollectionUtil;
import com.zx.health.commons.QiniuUtil;
import com.zx.health.commons.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import java.util.Iterator;
import java.util.Set;

@Component
public class Job {

    @Autowired
    private JedisPool jedisPool;
    public void deleteImgs(){
        Set<String> set = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        if(CollectionUtil.isNotEmpty(set)){
            Iterator<String> it = set.iterator();
            while(it.hasNext()){
                String fileName = it.next();
//                QiniuUtils.deleteFileFromQiniu(fileName);
                QiniuUtil.delete(fileName);
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
            }
        }
    }
}
